package com.epam.tat.services.mail;

import com.epam.tat.framework.bo.Account;
import com.epam.tat.framework.bo.Letter;
import com.epam.tat.pages.mail.*;

public class MailService {

    public void sendLetter(Account account, Letter letter) {
        new InboxPage()
                .writeNewLetter()
                .setTo(account.getLogin())
                .setSubject(letter.getSubject())
                .setText(letter.getText())
                .sendMail();
    }

    public void letterWithoutAddress(Letter letter) {
        new InboxPage()
                .writeNewLetter()
                .setSubject(letter.getSubject())
                .setText(letter.getText())
                .sendMail();
    }

    public void letterWithoutSubjectAndBody(Account account) {
        new InboxPage()
                .writeNewLetter()
                .setTo(account.getLogin())
                .sendMail();
        new NewMessagePage().acceptCheck();
    }

    public void createDraftAndDelete(Letter letter) {
        new InboxPage()
                .writeNewLetter()
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

    public boolean isMsgWithEmptySubjectPresented() {
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
