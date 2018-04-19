package com.epam.tat.steps;

import com.epam.tat.framework.bo.Account;
import com.epam.tat.framework.bo.AccountFactory;
import com.epam.tat.framework.bo.Letter;
import com.epam.tat.framework.bo.LetterFactory;
import com.epam.tat.services.AuthenticationService;
import com.epam.tat.services.mail.MailService;
import org.jbehave.core.annotations.*;

import static org.testng.Assert.*;

public class MailSteps {

    private AuthenticationService authService = new AuthenticationService();
    private Account account;
    private Letter letter = LetterFactory.getNewLetter();
    private MailService mailService = new MailService();

    @Given("Account with valid data")
    public void givenAccountWithValidData() {
        account = AccountFactory.getExistentAccount();
    }

    @Given("Account with invalid data")
    public void givenAccountWithInvalidData() {
        account = AccountFactory.getInvalidAccount();
    }

    @Given("Account with empty login")
    public void givenAccountWithEmptyLogin() {
        account = AccountFactory.getAccountWithoutLogin();
    }

    @Given("I signed in Mail.ru")
    public void whenISignedInMailRu() {
        account = AccountFactory.getExistentAccount();
        authService.mailSignIn(account);
    }

    @Given("Open 'New Message' page")
    public void givenOpenNewMessagePage() {
        mailService.openNewMsgPage();
    }

    @Given("Fill in all fields")
    public void givenFillInAllFields() {
        mailService.sendLetter(account, letter);
    }

    @Given("Fill in all fields except 'To'")
    public void givenFillInAllFieldsExceptTo() {
        mailService.letterWithoutAddress(letter);
    }

    @Given("Fill in only 'To'")
    public void givenFillInTo() {
        mailService.letterWithoutSubjectAndBody(account);
    }

    @Given("Create draft and delete it")
    public void givenCreateDraft() {
        mailService.createDraftAndDelete(letter);
    }

    @Given("Delete it from trash")
    public void givenDeleteFromTrash() {
        mailService.deleteMsgFromTrash(letter);
    }

    @When("I sign in with valid account")
    @Alias("I sign in with invalid account")
    public void whenISignInWithAccount() {
        authService.mailSignIn(account);
    }

    @When("I sign in without empty <login> <password>")
    public void whenISignInWithoutLoginOrPassword(@Named("login") String login, @Named("password") String password) {
        account.setLogin(login);
        account.setPassword(password);
        authService.mailSignIn(account);
    }

    @When("I click 'send letter'")
    public void whenIclickSend() {
        mailService.clickSendButton();
    }

    @When("I seek for the draft in trash")
    public void whenISeekForDraft() {
        mailService.seekForDeletedMsg(account, letter);
    }

    @Then("I see my login in the header bar")
    public void thenISeeMyLoginInTheHeaderBar() {
        assertTrue(authService.isLoggedIn());
    }

    @Then("I see an error")
    public void thenISeeEnError() {
        assertTrue(authService.loginPageHasError());
    }

    @Then("The letter should appear in Inbox and Sent")
    public void thenLetterShouldAppearInInboxAndSent() {
        assertTrue(mailService.isMessageInInboxAndSent(letter));
    }

    @Then("Error should be on screen")
    public void thenErrorShouldBeOnScreen() {
        assertTrue(mailService.isAlertPresented());
    }

    @Then("Alert should be displayed and accepted")
    public void thenAlertShouldBeAccepted() {
        mailService.acceptAlert();
    }

    @Then("Letter without subject should be in Inbox and Sent")
    public void thenLetterWithoutSubjShouldAppearInInboxAndSent() {
        assertTrue(mailService.isMsgWithEmptySubjectPresent());
    }

    @Then("I see 'Not found' result")
    public void thenISeeNothingFound() {
        assertTrue(mailService.notFoundResult());
    }
}
