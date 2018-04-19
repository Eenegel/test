package com.epam.tat.tests.mail;

import com.epam.tat.main.TestListener;
import com.epam.tat.pages.mail.*;
import com.epam.tat.tests.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Listeners(TestListener.class)
public class SendMailTest extends BaseTest {

    private String to = EMAIL;
    private String wrongAddress = "";
    private String subject = "TestMessage";
    private String emptySubject = "";
    private String text = "Hello World!";
    private String emptyBody = "";
    private String template = "TemplateMessage";

    @BeforeMethod
    public void logIn() throws InterruptedException {
        new MailHomePage(driver).correctAuthorization(EMAIL, CORRECT_PASS)
                .writeNewLetter();
    }

    @Test(description = "Create a new message with full fields correctly filled in. "
            + "Message must appear in Inbox and SentBox")
    public void testWithFullProperlyFilling() {
        new NewMessagePage(driver).properlyFillingFields(to, subject, text);
        assertTrue(new SentPage(driver).getSubject(subject)
                .contains(new InboxPage(driver).getSubject(subject)));
    }

    @Test(description = "Create new msg with wrong or empty address must return error")
    public void testWithWrongAddress() {
        NewMessagePage newMessagePage = new NewMessagePage(driver);
        newMessagePage.properlyFillingFields(wrongAddress, subject, text);
        assertTrue(newMessagePage.isAlertPresent());
    }

    @Test(description = "Create new message without body and subject must show an alert. "
            + "After the accept msg must appear in sent and inbox")
    public void testWithoutSubjectAndBody() {
        NewMessagePage newMessagePage = new NewMessagePage(driver);
        newMessagePage.properlyFillingFields(to, emptySubject, emptyBody);
        newMessagePage.acceptCheck();
        assertEquals(new InboxPage(driver).itemSubject(), new SentPage(driver).itemSubject());
    }

    @Test(description = "Create draft mail, delete it. Check, that mail doesn't exist")
    public void draftMail() throws InterruptedException {
        NewMessagePage newMessagePage = new NewMessagePage(driver);
        newMessagePage.createMsg(template, text);
        new DraftsPage(driver).deleteMsg(template);
        TrashPage trashPage = new TrashPage(driver);
        trashPage.checkMsg(template);
        trashPage.deleteMsg(template);
        trashPage.searchMsg(EMAIL, template);
        assertTrue(trashPage.notFoundResult());
    }

}
