package com.epam.tat.tests.mail;

import com.epam.tat.main.TestListener;
import com.epam.tat.pages.mail.MailHomePage;
import com.epam.tat.pages.mail.InboxPage;
import com.epam.tat.tests.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Listeners(TestListener.class)
public class LoginToMailTest extends BaseTest {

    public static final String INVALID_CRED = "Неверное имя или пароль";
    private String incorrectPass = "QazxsW1234";
    private By authFail = By.id("mailbox:authfail");

    @Test(description = "Login with correct data must return new logged mail page")
    public void testWithCorrectData() {
        MailHomePage homePage = new MailHomePage(driver);
        InboxPage inboxPage = homePage.correctAuthorization(EMAIL, CORRECT_PASS);
        assertEquals(inboxPage.checkAuthorization(), EMAIL);
    }

    @Test(description = "Login with incorrect data must return error on current page")
    public void testWithIncorrectData() {
        MailHomePage homePage = new MailHomePage(driver);
        homePage.incorrectAuthorization(EMAIL, incorrectPass);
        assertTrue(driver.findElement(authFail).getText().contains(INVALID_CRED));
    }

}
