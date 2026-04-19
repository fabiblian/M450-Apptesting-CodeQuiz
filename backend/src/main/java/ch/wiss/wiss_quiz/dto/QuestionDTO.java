package ch.wiss.wiss_quiz.dto;

import ch.wiss.wiss_quiz.model.Category;

import java.util.List;

public class QuestionDTO {

    private Integer id;
    private String question;
    private Category category;
    private List<AnswerDTO> answers;

    public QuestionDTO() {
    }
    public QuestionDTO(Integer id, String question, Category category) {
        this.id = id;
        this.question = question;
        this.category = category;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }


    public void setQuestion(String question) {
        this.question = question;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
