package com.epam.tat.tests.cloud;

import com.epam.tat.main.TestListener;
import com.epam.tat.pages.cloud.CloudHomePage;
import com.epam.tat.pages.cloud.CloudLogPage;
import com.epam.tat.pages.cloud.EMailPage;
import com.epam.tat.pages.mail.MailHomePage;
import com.epam.tat.tests.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Listeners(TestListener.class)
public class LoginToCloudTest extends BaseTest {

    private String incorrectPass = "QazxsW1234";

    @BeforeMethod
    public void switchToCloud() {
        MailHomePage homePage = new MailHomePage(driver);
        CloudLogPage cloudLogPage = homePage.switchToCloudPage();
        cloudLogPage.openLogForm();
    }

    @Test(description = "Login with correct data must return new logged cloud page")
    public void testLogInToCloudWithCorrectData() {
        CloudLogPage cloudLogPage = new CloudLogPage(driver);
        cloudLogPage.correctAuthorization(EMAIL, CORRECT_PASS);
        assertEquals(new CloudHomePage(driver).checkAuthorization(), EMAIL);
    }

    @Test(description = "Login with incorrect data must return error on new page")
    public void testWithIncorrectData() {
        CloudLogPage cloudLogPage = new CloudLogPage(driver);
        cloudLogPage.incorrectAuthorization(EMAIL, incorrectPass);
        new EMailPage(driver).invalidAuthorization();
        assertTrue(new EMailPage(driver).invalidAuthorization().contains(INVALID_CRED));
    }

}
