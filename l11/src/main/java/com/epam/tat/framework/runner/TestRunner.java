package com.epam.tat.framework.runner;

import org.testng.TestNG;
import org.testng.collections.Lists;

import java.util.List;

public class TestRunner {

    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        List<String> files = Lists.newArrayList();
        files.add("src/main/resources/testng.xml");
        testNG.setTestSuites(files);
        testNG.run();
    }

}
