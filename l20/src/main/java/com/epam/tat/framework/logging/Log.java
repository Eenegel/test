package com.epam.tat.framework.logging;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {
    static {
        PropertyConfigurator.configureAndWatch("src/main/resources/log4j.properties");
    }

    private static final Logger LOG = Logger.getLogger("com.epam.tat.framework.runner.TestRunner");

    public static void info(Object message) {
        LOG.info(message);
    }

    public static void error(Object message, Throwable throwable) {
        LOG.error(message, throwable);
    }

    public static void debug(Object message) {
        LOG.debug(message);
    }

}
