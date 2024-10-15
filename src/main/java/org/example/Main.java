package com.example.tests;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import java.time.Duration;
import java.util.List;
import java.net.HttpURLConnection;
import java.net.URL;
import io.qameta.allure.*;

public class Main {

    private WebDriver driver;
    private WebDriverWait wait;
    private int passedTests = 0;
    private int failedTests = 0;

    @BeforeClass
    public void setup() {
        // Set up Chrome WebDriver
        System.setProperty("webdriver.chrome.driver", "D:\\tester\\selenuim-test\\drivers\\chromedriver.exe"); // Update this path
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    @Description("Verifying Home Page Title")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Homepage")
    public void testHomePageTitle() {
        Reporter.log("Navigating to Home Page", true);
        driver.get("https://higene.net/");
        Assert.assertTrue(driver.getTitle().contains("Home"), "Home page title is incorrect");
        Reporter.log("Verified Home Page Title", true);
    }

    @Test(priority = 2)
    @Description("Verifying navigation to About page")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Navigation")
    public void testAboutPageNavigation() {
        driver.get("https://higene.net/");
        WebElement aboutLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='About']")));
        aboutLink.click();
        wait.until(ExpectedConditions.titleContains("About"));
        Assert.assertTrue(driver.getTitle().contains("About"), "Failed to navigate to About page");
    }

    @Test(priority = 3)
    @Description("Verifying content on About page")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Content")
    public void testAboutPageContent() {
        driver.get("https://higene.net/");
        WebElement aboutLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='About']")));
        aboutLink.click();
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Innovative Robotics')]")));
        Assert.assertTrue(heading.isDisplayed(), "About page heading is not displayed");
        Assert.assertTrue(heading.getText().contains("Innovative Robotics"), "Heading does not contain 'Innovative Robotics'");
    }

    @Test(priority = 4)
    @Description("Verifying navigation to Services page")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Navigation")
    public void testServicesPageNavigation() {
        driver.get("https://higene.net/");
        WebElement servicesLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Services']")));
        servicesLink.click();
        wait.until(ExpectedConditions.titleContains("Services"));
        Assert.assertTrue(driver.getTitle().contains("Services"), "Failed to navigate to Services page");
    }

    @Test(priority = 5)
    @Description("Verifying navigation to Contact Us page")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Navigation")
    public void testContactUsPageNavigation() {
        driver.get("https://higene.net/");
        WebElement contactUsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Contact Us']")));
        contactUsLink.click();
        wait.until(ExpectedConditions.titleContains("Contact Us"));
        Assert.assertTrue(driver.getTitle().contains("Contact Us"), "Failed to navigate to Contact Us page");
    }

    // Other tests with similar @Description, @Severity, and @Feature annotations
    @Test(priority = 6)
    @Description("test Valid Email Submission")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Submission")
    public void testValidEmailSubmission() throws InterruptedException {
        driver.get("https://higene.net/");

        // Wait for the page to fully load
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        // Navigate to 'Contact Us' page using XPath
        WebElement contactUsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Contact Us')]")));
        contactUsLink.click();

        // Switch to iframe if needed
        try {
            WebElement iframeElement = driver.findElement(By.xpath("//iframe[contains(@src, 'form')]"));
            driver.switchTo().frame(iframeElement);
        } catch (NoSuchElementException e) {
            System.out.println("No iframe found, continuing normally.");
        }

        // Wait for AJAX-based elements to load
        Thread.sleep(2000); // Adjust the time according to the page's load time

        // Retry logic for finding the firstName element
        for (int i = 0; i < 3; i++) {
            try {
                WebElement firstNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"post-33\"]/div/div[3]/div/div[2]/form/div[1]/input")));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstNameInput);
                firstNameInput.sendKeys("Mohamed");
                break; // Exit loop if element is found
            } catch (TimeoutException e) {
                System.out.println("Retrying... (" + (i + 1) + "/3)");
                if (i == 2) {
                    throw e; // Re-throw the exception after 3 retries
                }
            }
        }

        // Continue filling out the form and submit
        WebElement lastNameInput = driver.findElement(By.xpath("//*[@id=\"post-33\"]/div/div[3]/div/div[2]/form/div[2]/input"));
        lastNameInput.sendKeys("Nabil");

        WebElement emailInput = driver.findElement(By.xpath("//*[@id=\"post-33\"]/div/div[3]/div/div[2]/form/div[3]/input"));
        emailInput.sendKeys("mohamedn697@gmail.com");

        WebElement messageField = driver.findElement(By.xpath("//*[@id=\"post-33\"]/div/div[3]/div/div[2]/form/div[4]/textarea"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", messageField);
        messageField.sendKeys("This is a test message.");

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"post-33\"]/div/div[3]/div/div[2]/form/div[7]/button/div")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        submitButton.click();

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"post-33\"]/div/div[3]/div/div[2]/div[1]/span")));
        Assert.assertTrue(successMessage.isDisplayed(), "The form has been submitted successfully!");
    }

    @Test(priority = 7)
    @Description("test Invalid Email Submission")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Submission")
    public void testInvalidEmailSubmission() {
        driver.get("https://higene.net/");

        // Navigate using XPath for 'Contact Us'
        WebElement contactUsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Contact Us']")));
        contactUsLink.click();

        // Check if the form is inside an iframe and switch to it if necessary (adjust if iframe is found)
        // driver.switchTo().frame("frame_id_if_needed");

        // Explicit wait to ensure elements load
        WebElement firstNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"post-33\"]/div/div[3]/div/div[2]/form/div[1]/input")));
        firstNameInput.sendKeys("Mohamed");

        WebElement lastNameInput = driver.findElement(By.xpath("//*[@id=\"post-33\"]/div/div[3]/div/div[2]/form/div[2]/input"));
        lastNameInput.sendKeys("Nabil");

        // Enter an invalid email format
        WebElement emailInput = driver.findElement(By.xpath("//*[@id=\"post-33\"]/div/div[3]/div/div[2]/form/div[3]/input"));
        emailInput.sendKeys("x");  // Invalid email (single character)

        WebElement messageField = driver.findElement(By.xpath("//*[@id=\"post-33\"]/div/div[3]/div/div[2]/form/div[4]/textarea"));
        messageField.sendKeys("This is a test message.");

        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"post-33\"]/div/div[3]/div/div[2]/form/div[7]/button/div"));
        submitButton.click();



        // Switch back to the default content if switched to an iframe earlier
        driver.switchTo().defaultContent();
    }



    @Test(priority = 8)
    @Description("test Broken Links On HomePage")
    @Severity(SeverityLevel.NORMAL)
    @Feature("HomePage")
    public void testBrokenLinksOnHomePage() {
        driver.get("https://higene.net/");
        List<WebElement> links = driver.findElements(By.xpath("//a[@href]"));
        for (WebElement link : links) {
            String url = link.getAttribute("href");
            Assert.assertTrue(checkHttpStatus(url), "Broken link found: " + url);
        }
    }

    @Test(priority = 9)
    @Description("test Contact Us Without Message")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Contact us")
    public void testContactUsWithoutMessage() {
        driver.get("https://higene.net/");
        WebElement contactUsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Contact Us']")));
        contactUsLink.click();

        new WebDriverWait(driver, Duration.ofSeconds(20)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        WebElement firstNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"post-33\"]/div/div[3]/div/div[2]/form/div[1]/input")));
        firstNameInput.sendKeys("Mohamed");

        WebElement lastNameInput = driver.findElement(By.xpath("//*[@id=\"post-33\"]/div/div[3]/div/div[2]/form/div[2]/input"));
        lastNameInput.sendKeys("Nabil");
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"post-33\"]/div/div[3]/div/div[2]/form/div[3]/input")));
        emailInput.sendKeys("mohamedn697@gmail.com");

        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"post-33\"]/div/div[3]/div/div[2]/form/div[7]/button/div"));
        submitButton.click();


    }

    @Test(priority = 10)
    @Description("test Connect Now Button Functionality")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Contact Now")
    public void testConnectNowButtonFunctionality() {
        driver.get("https://higene.net/");

        // Check if the button is clickable using XPath
        WebElement connectNowButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ast-desktop-header\"]/div[1]/div/div/div/div[3]/div[2]/div/a[1]/div")));
        System.out.println("Connect Now button found and clickable.");

        // Click the button
        connectNowButton.click();

        // Print the current URL after clicking the button
        System.out.println("Current URL after clicking the button: " + driver.getCurrentUrl());

        // Wait until the URL contains "connect" and assert the condition
        wait.until(ExpectedConditions.titleContains("Contact Us"));
        Assert.assertTrue(driver.getTitle().contains("Contact Us"), "Failed to navigate to Contact Us page");
    }


    @AfterClass
    public void tearDown() {
        System.out.println("Tests passed: " + passedTests);
        System.out.println("Tests failed: " + failedTests);
        driver.quit();
    }

    @AfterMethod
    public void checkTestResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            failedTests++;
            Reporter.log("Test Failed: " + result.getName(), true);
        } else {
            passedTests++;
            Reporter.log("Test Passed: " + result.getName(), true);
        }
    }

    // Utility method to check the HTTP status of a URL (for broken link testing)
    private boolean checkHttpStatus(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            return connection.getResponseCode() < 400; // A valid link should return status < 400
        } catch (Exception e) {
            return false;
        }
    }
}