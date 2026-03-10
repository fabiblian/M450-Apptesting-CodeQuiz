package ch.wiss.wiss_quiz.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ch.wiss.wiss_quiz.model.Category;
import ch.wiss.wiss_quiz.model.CategoryRepository;
import ch.wiss.wiss_quiz.model.Question;
import ch.wiss.wiss_quiz.model.QuestionRepository;

@ExtendWith(MockitoExtension.class)
class QuizServiceMockitoTest {

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private QuizService service;

    
    @Test
    void getQuizQuestionsByCategoryId_throwsException_whenNotEnoughQuestionsExist() {
        Category category = new Category();
        List<Question> questions = List.of(
                new Question(),
                new Question()
        );

        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        when(questionRepository.findByCategory(category)).thenReturn(questions);

        assertThrows(IllegalStateException.class, () -> service.getQuizQuestionsByCategoryId(1));
    }
    
    // Simple example test to verify that JUnit works
    @Test
    void testSomething() {
    	assertEquals(4, 2 + 2);
    }
    
    @Test
    void getQuizQuestionsByCategoryId_returnsThreeQuestions_whenEnoughQuestionsExist() {
        Category category = new Category();
        List<Question> questions = List.of(
                new Question(),
                new Question(),
                new Question(),
                new Question()
        );

        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        when(questionRepository.findByCategory(category)).thenReturn(questions);

        List<Question> result = service.getQuizQuestionsByCategoryId(1);

        assertEquals(3, result.size());
    }

    @Test
    void getQuizQuestionsByCategoryId_throwsException_whenCategoryDoesNotExist() {
        when(categoryRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.getQuizQuestionsByCategoryId(99));
    }


}