package ch.wiss.wiss_quiz.controller;

import java.util.List;
import java.util.stream.Collectors;

import ch.wiss.wiss_quiz.dto.QuestionDTO;
import org.springframework.web.bind.annotation.*;

import ch.wiss.wiss_quiz.model.Answer;

import ch.wiss.wiss_quiz.model.AnswerRepository;
import ch.wiss.wiss_quiz.model.Category;
import ch.wiss.wiss_quiz.model.CategoryRepository;
import ch.wiss.wiss_quiz.model.Question;
import ch.wiss.wiss_quiz.model.QuestionRepository;

@RestController // This means that this class is a Controller
@RequestMapping(path="/question") // This means URL's start with /question (after Application path)
public class QuestionController {


    /**
     * Dependency Injection zu Respositories
     */
    private QuestionRepository questionRepository;

    private CategoryRepository categoryRepository;

    private AnswerRepository answerRepository;

    public QuestionController(QuestionRepository questionRepository, CategoryRepository categoryRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.categoryRepository = categoryRepository;
        this.answerRepository = answerRepository;

    }

    //@CrossOrigin(origins = "http://localhost:3000")


    @PostMapping(path = "/{cat_id}/addquestion")
    public String addNewQuestion(
            @PathVariable(value = "cat_id") Integer catId,
            @RequestBody QuestionDTO questionDTO) {

        // 1. Kategorie aus DB laden
        Category category = categoryRepository.findById(catId)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + catId));

        // 2. Question-Entity erstellen
        Question question = new Question();
        question.setQuestion(questionDTO.getQuestion()); // Der Text der Frage
        question.setCategory(category);

        // 3. Frage speichern (erzeugt die ID in der DB)
        Question savedQuestion = questionRepository.save(question);

        // 4. Antworten verarbeiten (Stream-Logik korrigiert)
        if (questionDTO.getAnswers() != null) {
            List<Answer> answers = questionDTO.getAnswers().stream()
                    .map(answerDto -> {
                        Answer answer = new Answer();
                        answer.setAnswer(answerDto.getText()); // Den Text aus dem DTO holen
                        answer.setCorrect(answerDto.isCorrect());    // Boolean-Wert setzen
                        answer.setQuestion(savedQuestion);           // Verknüpfung zur neuen Frage
                        return answer;
                    })
                    .collect(Collectors.toList());

            // 5. Alle Antworten auf einmal speichern
            answerRepository.saveAll(answers);
        }

        return "Frage und Antworten erfolgreich gespeichert!";
    }

  //@CrossOrigin(origins = "http://localhost:3000")
  @GetMapping(path="/all")
  public  Iterable<Question> getAllQuestions() {
    return questionRepository.findAll();
  } 
    
}