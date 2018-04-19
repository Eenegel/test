package com.epam.gomel.homework;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * @author Tatiana Ermolitskaya
 */
public class TestListener extends TestListenerAdapter {

    @Override
    public void onTestSuccess(ITestResult testResult) {
        System.out.println(testResult.getName() + " was successful.");
    }

    @Override
    public void onTestFailure(ITestResult testResult) {
        System.out.println(testResult.getName() + " was a failure. \n Throwable: "
                + testResult.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult testResult) {
        System.out.println(testResult.getName() + " was skipped.");
    }
}
