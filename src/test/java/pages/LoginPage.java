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

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased timeout
    }

    // Locators
    By username = By.name("username");
    By password = By.name("password");
    By loginButton = By.cssSelector("button[type='submit']");
    By errorMessage = By.cssSelector(".oxd-alert-content-text");
    By dashboardHeader = By.cssSelector(".oxd-topbar-header-breadcrumb");

    public void enterUsername(String user) {
        wait.until(ExpectedConditions.elementToBeClickable(username)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(username)).sendKeys(user);
    }

    public void enterPassword(String pass) {
        wait.until(ExpectedConditions.elementToBeClickable(password)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(password)).sendKeys(pass);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        sleep(3000); // Wait after clicking login
    }

    public String getErrorMessage() {
        sleep(2000); // Wait for error message animation
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    public boolean isLoginSuccessful() {
        sleep(2000); // Wait for page load
        try {
            WebElement dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader));
            return dashboard.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}