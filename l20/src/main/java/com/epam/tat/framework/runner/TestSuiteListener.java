package com.epam.tat.framework.runner;

import com.epam.tat.framework.logging.Log;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class TestSuiteListener implements ISuiteListener {

    @Override
    public void onStart(ISuite iSuite) {
        Log.info(String.format("Suit started : %s", iSuite.getXmlSuite().getFileName()));
    }

    @Override
    public void onFinish(ISuite iSuite) {
        Log.info(String.format("Suit finished : %s", iSuite.getXmlSuite().getFileName()));
    }
}
