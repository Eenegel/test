package com.epam.tat.framework.runner;

import com.epam.tat.framework.logging.Log;
import com.epam.tat.framework.ui.Browser;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter {

    @Override
    public void onStart(ITestContext testContext) {
        super.onStart(testContext);
        Log.info(String.format("[TEST STARTED] : %s", testContext.getName()));
    }

    @Override
    public void onTestSuccess(ITestResult testResult) {
        Log.info(String.format("[TEST SUCCESSFUL] : %s", testResult.getName()));
    }

    @Override
    public void onTestFailure(ITestResult testResult) {
        Log.info(String.format("[TEST FAILURE] : %s", testResult.getName()) + "\n Throwable: "
                + testResult.getThrowable().getMessage());
        Log.debug("Closing WebDriver and all associated windows");
        Browser.screenshot();
        Browser.getInstance().getWrappedDriver().quit();
    }

    @Override
    public void onTestSkipped(ITestResult testResult) {
        Log.info(String.format("[TEST SKIPPED] : %s", testResult.getName()));
    }

    @Override
    public void onFinish(ITestContext testContext) {
        super.onFinish(testContext);
        Log.info(String.format("[TEST FINISHED] : %s", testContext.getName()));
    }
}

