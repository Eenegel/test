package com.epam.gomel.homework;

import org.testng.TestNG;
import org.testng.collections.Lists;
import java.util.List;


/**
 * Created by Juicy J on 23.07.2017.
 */
public class TestRunner {
    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        //List<String> files = Arrays.asList("src/test/resources/testng.xml");
        List<String> files = Lists.newArrayList();
        files.add("src/test/resources/testng.xml");
        testNG.setTestSuites(files);
        testNG.run();

        /* XmlSuite suite = new XmlSuite();
        suite.setName("HomeWork suite");

        XmlTest test = new XmlTest(suite);
        test.setName("HomeWork test");

        List<XmlClass> classes = new ArrayList<XmlClass>();
        classes.add(new XmlClass("BoyTest"));
        classes.add(new XmlClass("GirlTest"));
        classes.add(new XmlClass("FactoryTest"));
        classes.add(new XmlClass("MokitoTest"));
        test.setXmlClasses(classes) ;

        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suites.add(suite);
        TestNG tng = new TestNG();
        tng.setXmlSuites(suites);
        tng.run();
*/
    }
}
