package ch.wiss.wiss_quiz.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import ch.wiss.wiss_quiz.model.Question;

class QuizServiceTest {

    private QuizService service;

    @BeforeEach
    void setUp() {
        service = new QuizService(null, null);
    }

    // Simple example test to verify that JUnit works
    @Test
    void testSomething() {
    	assertEquals(4, 2 + 2);
    }
    
    
    @Test
    void returnsAllIfLessOrEqualMax() {
        List<Question> input = List.of(new Question(), new Question());

        List<Question> result = service.pickQuizQuestions(input, 3);

        assertEquals(2, result.size());
    }

    @Test
    void limitsToMaxIfMoreThanMax() {
        List<Question> input = List.of(
                new Question(), new Question(), new Question(), new Question(), new Question()
        );

        List<Question> result = service.pickQuizQuestions(input, 3);

        assertEquals(3, result.size());
    }

    @Test
    void doesNotModifyOriginalList() {
        List<Question> input = new ArrayList<>(List.of(
                new Question(), new Question(), new Question(), new Question()
        ));

        service.pickQuizQuestions(input, 3);

        assertEquals(4, input.size());
    }

    @Test
    void handlesBadInputs() {
        assertTrue(service.pickQuizQuestions(null, 3).isEmpty());
        assertTrue(service.pickQuizQuestions(List.of(), 3).isEmpty());
        assertTrue(service.pickQuizQuestions(List.of(new Question()), 0).isEmpty());
    }
}