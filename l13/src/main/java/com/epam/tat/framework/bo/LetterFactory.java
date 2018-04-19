package com.epam.tat.framework.bo;

import org.apache.commons.lang3.RandomStringUtils;

public class LetterFactory {

    public static final int COUNT = 8;

    private static String subject = RandomStringUtils.randomAlphanumeric(COUNT);
    private static String text = RandomStringUtils.randomAlphanumeric(COUNT);

    public static Letter getNewLetter() {
        return new Letter(subject, text);
    }

}
