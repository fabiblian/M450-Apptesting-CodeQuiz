import { render, screen } from "@testing-library/react";
import QuestionList from "./QuestionList";

describe("QuestionList", () => {
  beforeEach(() => {
    global.fetch = jest.fn(() => new Promise(() => {}));
  });

  afterEach(() => {
    jest.resetAllMocks();
  });

  test("table is properly rendered", async () => {
    const questions = [
      {
        id: "myId",
        question: "Meine BeispielFrage",
        answers: [
          { id: 1, answer: "Antwort 1", correct: true },
          { id: 2, answer: "Antwort 2", correct: false },
          { id: 3, answer: "Antwort 3", correct: false }
        ]
      }
    ];

    render(<QuestionList questions={questions} />);

    expect(await screen.findByText("Meine BeispielFrage")).toBeInTheDocument();
    expect(screen.getByText("Antwort 1")).toBeInTheDocument();
    expect(screen.getByText("Antwort 2")).toBeInTheDocument();
    expect(screen.getByText("Antwort 3")).toBeInTheDocument();
    expect(screen.getByText("Wird nicht verraten ;)")).toBeInTheDocument();
  });
});
