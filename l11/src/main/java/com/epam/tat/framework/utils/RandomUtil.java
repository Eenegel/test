package com.epam.tat.framework.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.io.IOException;

public class RandomUtil {

    public static final String FILE_PATH = "src/main/resources/generatedFiles/%s.txt";
    public static final String ERROR_MESSAGE = "File %s not found!";
    public static final int COUNT = 8;

    public static String getRandomFolder() {
        return RandomStringUtils.randomAlphanumeric(COUNT);
    }

    public static File getRandomFile() {
        File file = new File(String.format(FILE_PATH, String.valueOf(RandomStringUtils.randomNumeric(COUNT))));
        try {
            FileUtils.writeStringToFile(file, "Hello world!", "UTF-8");
        } catch (IOException e) {
            System.out.println(String.format(ERROR_MESSAGE, file.getAbsolutePath()));
        }
        return file;
    }

    public static String getRandomLogin() {
        return RandomStringUtils.randomAlphanumeric(COUNT);
    }

    public static String getRandomPassword() {
        return RandomStringUtils.randomAlphanumeric(COUNT);
    }
}
