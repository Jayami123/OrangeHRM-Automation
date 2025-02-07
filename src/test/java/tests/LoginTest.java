package tests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Jayami/Downloads/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new LoginPage(driver);
        sleep(2000); // Wait for initial page load
    }

    @Test
    public void testInvalidLogin() {
        loginPage.enterUsername("invalidUser");
        loginPage.enterPassword("invalidPass");
        loginPage.clickLogin();
        String expectedError = "Invalid credentials";
        String actualError = loginPage.getErrorMessage();
        System.out.println("Expected error message: " + expectedError);
        System.out.println("Actual error message received: " + actualError);
        Assert.assertEquals(actualError, expectedError, "Error message mismatch!");
        sleep(3000);
    }

    @Test
    public void testValidLogin() {
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();
        boolean isSuccess = loginPage.isLoginSuccessful();
        Assert.assertTrue(isSuccess, "Login was not successful!");
        sleep(3000); // Extended delay to see dashboard
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            sleep(3000); // Extended delay before closing
            driver.quit();
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