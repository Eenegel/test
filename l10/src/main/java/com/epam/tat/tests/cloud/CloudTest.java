package com.epam.tat.tests.cloud;

import com.epam.tat.main.TestListener;
import com.epam.tat.pages.cloud.CloudHomePage;
import com.epam.tat.pages.cloud.CloudLogPage;
import com.epam.tat.pages.cloud.PublicPage;
import com.epam.tat.pages.mail.MailHomePage;
import com.epam.tat.tests.BaseTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners(TestListener.class)
public class CloudTest extends BaseTest {

    public static final int MIN_VALUE = 5;
    public static final int MAX_VALUE = 10;

    @BeforeMethod
    public void logIn() {
        new MailHomePage(driver).switchToCloudPage().openLogForm();
        new CloudLogPage(driver).correctAuthorization(EMAIL, CORRECT_PASS);
    }

    @Test(description = "New folder should be created with a defined folder name")
    public void testCreateNewFolder() {
        String folderName = RandomStringUtils.randomAlphabetic(MIN_VALUE, MAX_VALUE);
        CloudHomePage cloudHomePage = new CloudHomePage(driver);
        cloudHomePage.createFolder(folderName);
        assertTrue(cloudHomePage.checkFolderIsPresent(folderName));
    }

    @Test(description = "New folder should be deleted from the cloud")
    public void testDeleteFolder() {
        String folderName = RandomStringUtils.randomAlphabetic(MIN_VALUE, MAX_VALUE);
        CloudHomePage cloudHomePage = new CloudHomePage(driver);
        cloudHomePage.createFolder(folderName);
        cloudHomePage.deleteFolder(folderName);
        assertTrue(cloudHomePage.checkFolderIsNotPresent(folderName));
    }

    @Test(description = "Uploaded file should appear in the root folder")
    public void testUploadTheFileToRootFolder() {
        CloudHomePage cloudHomePage = new CloudHomePage(driver);
        String uploadedFileName = cloudHomePage.uploadFile(FILE_PATH);
        assertTrue(cloudHomePage.checkFolderIsPresent(uploadedFileName));
    }

    @Test(description = "Uploaded file should appear in the root folder")
    public void testDragAndDropFile() {
        String folderName = RandomStringUtils.randomAlphabetic(MIN_VALUE, MAX_VALUE);
        CloudHomePage cloudHomePage = new CloudHomePage(driver);
        String uploadedFileName = cloudHomePage.uploadFile(FILE_PATH);
        cloudHomePage.createFolder(folderName);
        cloudHomePage.dragAndDrop(uploadedFileName, folderName);
        cloudHomePage.openFolder(folderName);
        assertTrue(cloudHomePage.checkFolderIsPresent(uploadedFileName));
    }

    @Test(description = "Chosen file should be highlighted before sharing the link. shared link "
            + "should work and show shared element")
    public void testShareLink() {
        CloudHomePage cloudHomePage = new CloudHomePage(driver);
        String expectedTitle = cloudHomePage.shareTheElement();
        cloudHomePage.checkSharedElement();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        String actualTitle = new PublicPage(driver).getTitleText();
        assertEquals(actualTitle, expectedTitle);
    }
}
