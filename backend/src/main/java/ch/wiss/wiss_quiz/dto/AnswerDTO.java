package ch.wiss.wiss_quiz.dto;

import java.util.List;

public class AnswerDTO {

    private  Long  id;
    private String text;
    private List<AnswerDTO> answers;
    private boolean correct;


    public AnswerDTO() {}

    public AnswerDTO(Long id, String text, List<AnswerDTO> answers, boolean correct) {
        this.id = id;
        this.text = text;
        this.answers = answers;
        this.correct = correct;

    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }



}
