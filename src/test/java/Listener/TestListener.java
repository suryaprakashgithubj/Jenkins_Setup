package Listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {

        System.out.println("Test Execution Started");
    }

    @Override
    public void onFinish(ITestContext context) {

        System.out.println("Test Execution Finished");
    }

    @Override
    public void onTestStart(ITestResult result) {

        System.out.println("Test Case Started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        System.out.println("Test Case Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        System.out.println("Test Case Failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        System.out.println("Test Case Skipped");
    }
}