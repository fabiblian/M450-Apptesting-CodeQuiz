package ch.wiss.wiss_quiz.selenium;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class SeleniumTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Helper Methoden (dürfen verwendet werden)
    private void openQuizPage() {
        driver.get("http://localhost:3000/quiz");
    }

    private WebElement questionText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-testid='question-text']")));
    }

    private List<WebElement> answerButtons() {
        return driver.findElements(By.cssSelector("[data-testid^='answer-button-']"));
    }

    private WebElement nextButton() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-testid='next-button']")));
    }

    // ============================
    // 🟢 Beispieltests (gegeben)
    // ============================

    @Test
    void quizPage_showsQuestionText() {
        openQuizPage();

        String text = questionText().getText();

        assertFalse(text.isBlank(), "Der Fragetext sollte sichtbar sein.");
    }

    @Test
    void quizPage_showsAnswerButtons() {
        openQuizPage();

        wait.until(driver -> answerButtons().size() > 0);

        List<WebElement> buttons = answerButtons();

        assertFalse(buttons.isEmpty(), "Antwort-Buttons sollten sichtbar sein.");
        assertEquals(4, buttons.size(), "Es sollten 4 Antworten vorhanden sein.");
    }

    // ============================
    // 🟡 Aufgabe 1
    // ============================

    @Test
    void clickingAnswer_changesPageState() {
        openQuizPage();

        String questionBefore = questionText().getText();

        // 1. Warte auf Antwort-Buttons
        wait.until(driver -> answerButtons().size() > 0);
        // 2. Klicke auf eine Antwort
        answerButtons().get(0).click();
        // 3. Hole den Next-Button
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-testid='next-button']")
            ));
    
        // 4. Prüfe, dass er sichtbar ist
        String bodyText = driver.findElement(By.tagName("body")).getText();

        // TODO:Prüfe, dass nicht gilt: bodyText.isBlank()
        assertTrue(false);
    }

    // ============================
    // 🟡 Aufgabe 2
    // ============================

    @Test
    void nextButton_isVisible_afterAnswer() {
        openQuizPage();

        // TODO:
        // 1. Warte auf Antwort-Buttons
        // 2. Klicke auf eine Antwort
        // 3. Hole den Next-Button
        // 4. Prüfe, dass er sichtbar ist
        assertTrue(false);
    }

    // ============================
    // 🔴 Aufgabe 3
    // ============================

    @Test
    void nextButton_loadsNextQuestion() {
        openQuizPage();

        // TODO:
        // 1. Speichere erste Frage
        // 2. Klicke Antwort
        // 3. Klicke Next
        // 4. Prüfe, dass neue Frage angezeigt wird
        assertTrue(false);
    }

    // ============================
    // 🔴 Aufgabe 4 (schwer)
    // ============================

    @Test
    void correctAnswer_increasesScore() {
        openQuizPage();

        // TODO:
        // 1. Klicke die richtige Antwort (Hinweis: index beachten!)
        // 2. Warte auf Score-Anzeige
        // 3. Prüfe, dass Score = 100 ist
        assertTrue(false);
    }

    // ============================
    // 🔴 Aufgabe 5 (Bonus)
    // ============================

    @Test
    void quiz_can_progress_through_multiple_questions() {
        openQuizPage();

        // TODO:
        // 1. Mehrere Fragen nacheinander beantworten
        // 2. Next klicken
        // 3. Prüfen, dass sich die Fragen ändern
        assertTrue(false);
    }
    
    // ============================
    // 🔴 Aufgabe 6 (Bonus)
    // ============================  
    @Test
    void selectCategory_and_answerFirstQuestion_correctly() {

        // TODO:
        // 1. Öffne die Seite (Startseite, nicht /quiz!)
        // 2. Finde die Kategorie-Combobox
        // 3. Wähle "Applikationen testen"
        // 4. Klicke auf "Quiz starten"
        // 5. Warte bis die Frage erscheint
        // 6. Klicke die richtige Antwort
        // 7. Prüfe, dass der Score 100 ist
    	assertTrue(false);
    }
}