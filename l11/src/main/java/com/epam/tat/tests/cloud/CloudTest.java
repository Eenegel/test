package com.epam.tat.tests.cloud;

import com.epam.tat.framework.bo.Account;
import com.epam.tat.framework.bo.AccountFactory;
import com.epam.tat.framework.runner.TestListener;
import com.epam.tat.framework.ui.Browser;
import com.epam.tat.framework.utils.RandomUtil;
import com.epam.tat.services.AuthenticationService;
import com.epam.tat.services.cloud.CloudService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners(TestListener.class)
public class CloudTest {

    private AuthenticationService authService;
    private Account account;
    private CloudService cloudService;

    @BeforeMethod
    public void logIn() {
        authService = new AuthenticationService();
        cloudService = new CloudService();
        account = AccountFactory.getExistentAccount();
        authService.switchToCloud();
        authService.cloudSignIn(account);
    }

    @Test(description = "New folder should be created with a defined folder name")
    public void testCreateNewFolder() {
        String folderName = RandomUtil.getRandomFolder();
        cloudService.createFolder(folderName);
        assertTrue(cloudService.checkCreatedFolder(folderName));
    }

    @Test(description = "New folder should be deleted from the cloud")
    public void testDeleteFolder() {
        String folderName = RandomUtil.getRandomFolder();
        cloudService.createAndDeleteFolder(folderName);
        assertTrue(cloudService.checkFolder(folderName));
    }

    @Test(description = "Uploaded file should appear in the root folder")
    public void testUploadTheFileToRootFolder() {
        File file = RandomUtil.getRandomFile();
        String fileName = cloudService.uploadFile(file);
        assertTrue(cloudService.checkCreatedFolder(fileName));
    }

    @Test(description = "Uploaded file should appear in the root folder")
    public void testDragAndDropFile() {
        String folderName = RandomUtil.getRandomFolder();
        File file = RandomUtil.getRandomFile();
        String uploadedFileName = cloudService.uploadFile(file);
        cloudService.dragAndDropFile(folderName, uploadedFileName);
        assertTrue(cloudService.checkFolder(uploadedFileName));
    }

    @Test(description = "Chosen file should be highlighted before sharing the link. shared link "
                    + "should work and show shared element")
    public void testShareLink() {
        String expectedTitle = cloudService.shareTheElement();
        cloudService.checkSharedElement();
        for (String winHandle : Browser.getInstance().getWrappedDriver().getWindowHandles()) {
            Browser.getInstance().getWrappedDriver().switchTo().window(winHandle);
        }
        String actualTitle = cloudService.titleOfSharedElement();
        assertEquals(actualTitle, expectedTitle);
    }

    @AfterMethod
    public void tearDown() {
        Browser.getInstance().stopBrowser();
    }
}
