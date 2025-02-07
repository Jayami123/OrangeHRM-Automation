package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    By username = By.name("username");  // Using more reliable locators
    By password = By.name("password");
    By loginButton = By.cssSelector("button[type='submit']");
    By errorMessage = By.xpath("/html/body/div[1]/div[1]/div/div[1]/div/div[2]/div[2]/div/div[1]/div[1]/p");

    // Actions
    public void enterUsername(String user) {
        wait.until(ExpectedConditions.presenceOfElementLocated(username)).sendKeys(user);
    }

    public void enterPassword(String pass) {
        wait.until(ExpectedConditions.presenceOfElementLocated(password)).sendKeys(pass);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public String getErrorMessage() {
        // Wait a bit after clicking login before looking for the error message
        try {
            Thread.sleep(1000); // Small delay to allow the error message to appear
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Now wait for the error message to be present AND visible
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(errorMessage));
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }
}