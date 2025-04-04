

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomTestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName());
        System.out.println("Error: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test suite finished: " + context.getName());
        System.out.println("Total tests passed: " + context.getPassedTests().size());
        System.out.println("Total tests failed: " + context.getFailedTests().size());
    }
}
