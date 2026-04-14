import { fireEvent, render, screen, waitFor } from "@testing-library/react";
import GameSession from "./GameSession";

const categories = [
  { id: 1, name: "database" },
  { id: 4, name: "Applikationen testen" }
];

const createQuestion = (id, question, correctAnswer, wrongAnswer = "Falsch") => ({
  id,
  question,
  answers: [
    { id: id * 10 + 1, answer: correctAnswer, correct: true },
    { id: id * 10 + 2, answer: wrongAnswer, correct: false }
  ]
});

describe("GameSession", () => {
  beforeEach(() => {
    global.fetch = jest.fn(() => new Promise(() => {}));
  });

  afterEach(() => {
    jest.resetAllMocks();
  });

  test("renders provided question and answers", async () => {
    const questions = [
      createQuestion(1, "Meine BeispielFrage", "Antwort 1", "Antwort 2")
    ];

    render(<GameSession questions={questions} />);

    expect(await screen.findByText("Meine BeispielFrage")).toBeInTheDocument();
    expect(screen.getByText("Antwort 1")).toBeInTheDocument();
    expect(screen.getByText("Antwort 2")).toBeInTheDocument();
  });

  test("selecting right answer displays success", async () => {
    const questions = [
      createQuestion(1, "Meine BeispielFrage", "Antwort 1", "Antwort 2")
    ];

    render(<GameSession questions={questions} />);

    fireEvent.click(await screen.findByText("Antwort 1"));

    expect(await screen.findByText("Hurra")).toBeInTheDocument();
  });

  test("selecting wrong answer displays failure", async () => {
    const questions = [
      createQuestion(1, "Meine BeispielFrage", "Antwort 1", "Antwort 2")
    ];

    render(<GameSession questions={questions} />);

    fireEvent.click(await screen.findByText("Antwort 2"));

    expect(await screen.findByText("Ohh nein")).toBeInTheDocument();
  });

  test("renders category dropdown and start button and loads initial quiz", async () => {
    global.fetch = jest
      .fn()
      .mockResolvedValueOnce({
        json: () => Promise.resolve(categories)
      })
      .mockResolvedValueOnce({
        json: () => Promise.resolve([createQuestion(11, "DB Frage", "SELECT")])
      });

    render(<GameSession />);

    expect(await screen.findByTestId("category-select")).toBeInTheDocument();
    expect(screen.getByTestId("start-quiz-button")).toBeInTheDocument();

    await waitFor(() => {
      expect(global.fetch).toHaveBeenNthCalledWith(1, "http://localhost:8080/category");
      expect(global.fetch).toHaveBeenNthCalledWith(2, "http://localhost:8080/quiz?cat_id=1");
    });

    expect(await screen.findByText("DB Frage")).toBeInTheDocument();
  });

  test("changing category and restarting quiz loads selected category and resets score", async () => {
    global.fetch = jest
      .fn()
      .mockResolvedValueOnce({
        json: () => Promise.resolve(categories)
      })
      .mockResolvedValueOnce({
        json: () =>
          Promise.resolve([
            createQuestion(11, "DB Frage", "SELECT", "INSERT")
          ])
      })
      .mockResolvedValueOnce({
        json: () =>
          Promise.resolve([
            createQuestion(41, "Test Frage", "JUnit", "Docker")
          ])
      });

    render(<GameSession />);

    fireEvent.click(await screen.findByText("SELECT"));
    expect(await screen.findByText("Hurra")).toBeInTheDocument();
    expect(screen.getByTestId("score-text")).toHaveTextContent("Score: 100");

    fireEvent.change(screen.getByTestId("category-select"), {
      target: { value: "4" }
    });
    fireEvent.click(screen.getByTestId("start-quiz-button"));

    await waitFor(() => {
      expect(global.fetch).toHaveBeenNthCalledWith(3, "http://localhost:8080/quiz?cat_id=4");
    });

    expect(await screen.findByText("Test Frage")).toBeInTheDocument();
    expect(screen.getByTestId("score-text")).toHaveTextContent("Score: 0");
    expect(screen.queryByText("Hurra")).not.toBeInTheDocument();
  });
});
