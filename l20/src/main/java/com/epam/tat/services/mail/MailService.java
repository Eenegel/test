package com.epam.tat.services.mail;

import com.epam.tat.framework.bo.Account;
import com.epam.tat.framework.bo.Letter;
import com.epam.tat.framework.logging.Log;
import com.epam.tat.pages.mail.*;

public class MailService {

    public void sendLetter(Account account, Letter letter) {
        Log.debug(String.format("Trying to send letter with address: %s, subject: %s, text: %s",
                account.getLogin(), letter.getSubject(), letter.getText()));
        new NewMessagePage()
                .setTo(account.getLogin())
                .setSubject(letter.getSubject())
                .setText(letter.getText());
    }

    public void openNewMsgPage() {
        new InboxPage()
                .writeNewLetter();
    }

    public void clickSendButton() {
        new NewMessagePage().sendMail();
    }

    public void letterWithoutAddress(Letter letter) {
        Log.debug(String.format("Trying to send letter with subject: %s, text: %s",
                letter.getSubject(), letter.getText()));
        new NewMessagePage()
                .setSubject(letter.getSubject())
                .setText(letter.getText());
    }

    public void letterWithoutSubjectAndBody(Account account) {
        Log.debug(String.format("Trying to send letter with address: %s",
                account.getLogin()));
        new InboxPage()
                .writeNewLetter()
                .setTo(account.getLogin());
    }

    public void acceptAlert() {
        new NewMessagePage().acceptCheck();
    }

    public void createDraftAndDelete(Letter letter) {
        Log.debug(String.format("Trying to create draft with subject: %s, text: %s",
                letter.getSubject(), letter.getText()));
        new NewMessagePage()
                .setSubject(letter.getSubject())
                .setText(letter.getText())
                .saveDraft();
        new DraftsPage().deleteMsg(letter.getSubject());
    }

    private String getSubjectOfSentMsg(Letter letter) {
        return new SentPage().getSubject(letter.getSubject());
    }

    private String getSubjectOfReceivedMsg(Letter letter) {
        return new InboxPage().getSubject(letter.getSubject());
    }

    public boolean isMessageInInboxAndSent(Letter letter) {
        return getSubjectOfSentMsg(letter).contains(getSubjectOfReceivedMsg(letter));
    }

    public boolean isAlertPresented() {
        return new NewMessagePage().isAlertPresent();
    }

    private String itemSubjectSent() {
        return new SentPage().itemSubject();
    }

    private String itemSubjectInbox() {
        return new InboxPage().itemSubject();
    }

    public boolean isMsgWithEmptySubjectPresent() {
        return itemSubjectSent().equals(itemSubjectInbox());
    }

    public void deleteMsgFromTrash(Letter letter) {
        new TrashPage().deleteMsg(letter.getSubject());
    }

    public void seekForDeletedMsg(Account account, Letter letter) {
        new TrashPage().searchMsg(account.getLogin(), letter.getSubject());
    }

    public boolean notFoundResult() {
        return new TrashPage().notFoundResult();
    }
}
