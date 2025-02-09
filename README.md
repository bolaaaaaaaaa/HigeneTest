# Selenium Test Automation Project

## Overview
This project contains automated test cases for testing the functionality of the website [HiGene](https://higene.net/) using Selenium WebDriver and TestNG. The test suite verifies various web pages, form submissions, and broken links to ensure the website's reliability.

## Technologies Used
- **Java** (Programming Language)
- **Selenium WebDriver** (Web Automation Framework)
- **TestNG** (Testing Framework)
- **Allure Reports** (Test Reporting)
- **Chrome WebDriver** (Browser Automation)

## Prerequisites
Ensure you have the following installed:
- Java Development Kit (JDK) 8 or later
- Maven (for dependency management)
- Chrome Browser (latest version)
- ChromeDriver (matching your Chrome version)
- IntelliJ IDEA (or any Java IDE)

## Project Setup
1. Clone this repository:
   ```sh
   git clone https://github.com/your-repo/selenium-test-project.git
   ```
2. Navigate to the project directory:
   ```sh
   cd selenium-test-project
   ```
3. Install dependencies using Maven:
   ```sh
   mvn clean install
   ```
4. Update the ChromeDriver path in `Main.java`:
   ```java
   System.setProperty("webdriver.chrome.driver", "D:\\tester\\selenuim-test\\drivers\\chromedriver.exe");
   ```

## Test Cases
The test suite includes the following test cases:
1. **Home Page Title Verification**
2. **Navigation to About Page**
3. **Content Verification on About Page**
4. **Navigation to Services Page**
5. **Navigation to Contact Us Page**
6. **Valid Email Submission in Contact Form**
7. **Invalid Email Submission in Contact Form**
8. **Broken Links Verification on Home Page**
9. **Contact Us Form Submission Without Message**
10. **Connect Now Button Functionality Test**

## Running the Tests
You can execute the test suite using TestNG:
```sh
mvn test
```
Or run the `Main.java` class from your IDE.

## Test Results & Reporting
- The results of test execution can be viewed in the TestNG reports generated in the `target/surefire-reports` folder.
- Allure reports can be generated using:
  ```sh
  mvn allure:serve
  ```

## After Test Execution
- The script will print the number of passed and failed tests.
- The browser will automatically close after execution.



