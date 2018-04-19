package com.epam.tat.framework.bo;

public class Letter {

    private String subject;
    private String text;

    public Letter(String subject, String text) {
        this.subject = subject;
        this.text = text;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }
}
