package com.epam.tat.framework.runner;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.Parameter;
import com.epam.tat.framework.ui.BrowserType;

import java.util.ArrayList;
import java.util.List;

public class Parameters {

    private static Parameters instance;

    @Parameter(names = {"--chrome", "-c"}, description = "Path to Google Chrome driver")
    private String chromeDriver = "src/main/resources/chromedriver.exe";

    @Parameter(names = "--help", help = true, description = "How to use")
    private boolean help;

    @Parameter(names = {"--browser", "-b"}, description = "Browser type", converter = BrowserTypeConverter.class)
    private BrowserType browserType = BrowserType.CHROME;

    @Parameter(names = {"--suite", "-s"}, description = "Suite to launch")
    private List<String> suites = new ArrayList<String>();

    public static synchronized Parameters instance() {
        if (instance == null) {
            instance = new Parameters();
        }
        return instance;
    }

    public String getChromeDriver() {
        return chromeDriver;
    }

    public boolean isHelp() {
        return help;
    }

    public List<String> getSuites() {
        return suites;
    }

    public BrowserType getBrowserType() {
        return browserType;
    }

    public static class BrowserTypeConverter implements IStringConverter<BrowserType> {
        public BrowserType convert(String s) {
            return BrowserType.valueOf(s.toUpperCase());
        }
    }
}
