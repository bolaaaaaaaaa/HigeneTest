import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class ContactUsTest {

    WebDriver driver;
    WebDriverWait wait;

    @Test(priority = 7)
    public void testInvalidEmailSubmission() {
        driver.get("https://higene.net/");

        // Using CSS selector for the 'Contact Us' link
        driver.findElement(By.cssSelector("a[href='/contact']")).click();
        System.out.println("Clicked on 'Contact Us' link");

        // Using CSS selectors for the input fields (ensure these IDs are static and correct)
        driver.findElement(By.cssSelector("input#text")).sendKeys("Mohamed");
        System.out.println("Entered first name");

        driver.findElement(By.cssSelector("input#7640798f")).sendKeys("Nabil");
        System.out.println("Entered last name");

        driver.findElement(By.cssSelector("input#3cc96c1e")).sendKeys("invalid-email");
        System.out.println("Entered invalid email");

        driver.findElement(By.cssSelector("textarea#27c1e90c")).sendKeys("This is a test message.");
        System.out.println("Entered test message");

        // Using CSS selector for the submit button
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        System.out.println("Clicked on submit button");

        // Adding explicit wait for the error message to appear
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#emailError")));

        // Assertion to check if the error message is displayed
        Assert.assertTrue(emailError.isDisplayed(), "Error message not displayed for invalid email.");
        System.out.println("Error message displayed as expected");
    }
}
