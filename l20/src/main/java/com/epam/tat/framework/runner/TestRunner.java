package com.epam.tat.framework.runner;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.epam.tat.framework.logging.Log;
import org.testng.TestNG;

import java.util.Arrays;

public class TestRunner {

    public static TestNG configureTestNG() {
        TestNG testNG = new TestNG();
        testNG.addListener(new TestSuiteListener());
        testNG.addListener(new TestListener());
        testNG.setTestSuites(Arrays.asList("src/main/resources/testng.xml"));
        return testNG;
    }

    public static void main(String[] args) {
        Log.info("Parse cli");
        parceCli(args);
        Log.info("Start app...");
        configureTestNG().run();
        Log.info("End app.");
    }

    private static void parceCli(String[] args) {
        Log.info("Parse cli's using JCommander");
        JCommander jCommander = new JCommander(Parameters.instance());
        try {
            jCommander.parse(args);
        } catch (ParameterException e) {
            Log.error(e.getMessage(), e);
            jCommander.usage();
            System.exit(1);
        }
        if (Parameters.instance().isHelp()) {
            jCommander.usage();
            System.exit(0);
        }
    }

}
