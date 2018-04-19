package com.epam.tat.tests.mail;

import com.epam.tat.framework.bo.*;
import com.epam.tat.framework.ui.Browser;
import com.epam.tat.services.AuthenticationService;
import com.epam.tat.services.mail.MailService;
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class SendMailTest {

    private Account account = AccountFactory.getExistentAccount();
    private Letter letter = LetterFactory.getNewLetter();
    private MailService mailService = new MailService();

    @BeforeMethod
    public void logIn() {
        new AuthenticationService().mailSignIn(account);
    }

    @Test(description = "Create a new message with full fields correctly filled in. "
            + "Message must appear in Inbox and SentBox")
    public void testWithFullProperlyFilling() {
        mailService.sendLetter(account, letter);
        assertTrue(mailService.isMessageInInboxAndSent(letter));
    }

    @Test(description = "Create new msg with wrong or empty address must return error")
    public void testWithWrongAddress() {
        mailService.letterWithoutAddress(letter);
        assertTrue(mailService.isAlertPresented());
    }

    @Test(description = "Create new message without body and subject must show an alert. "
            + "After the accept msg must appear in sent and inbox")
    public void testWithoutSubjectAndBody() {
        mailService.letterWithoutSubjectAndBody(account);
        assertTrue(mailService.isMsgWithEmptySubjectPresented());
    }

    @Test(description = "Create draft mail, delete it. Check, that mail doesn't exist")
    public void draftMail() throws InterruptedException {
        mailService.createDraftAndDelete(letter);
        mailService.deleteMsgFromTrash(letter);
        mailService.seekForDeletedMsg(account, letter);
        assertTrue(mailService.notFoundResult());
    }

    @AfterMethod
    public void tearDown() {
        Browser.getInstance().stopBrowser();
    }

}
