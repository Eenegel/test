package com.epam.tat.pages.cloud;

import com.epam.tat.framework.ui.Browser;
import com.epam.tat.framework.ui.Element;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;

import static org.openqa.selenium.By.*;

public class CloudHomePage {

    private Element authMenu = new Element(id("PH_user-email"));
    private Element create = new Element(xpath("//div[@id='cloud_toolbars']//div[@data-group='create']"
            + "//div[@data-mnemo='ctrl']"));
    private Element createFolder = new Element(cssSelector("div[class='b-dropdown__list']:not([style*='none'])"
            + " a[data-name='folder']"));
    private Element changeCreatedFolderName = new Element(cssSelector(".layer__input"));
    private String findFile = "//div[@class='b-filename__spacer' and contains(text(), '%s')]/parent::div";
    private Element createButton = new Element(xpath("//button[@data-name='add']"));
    private Element deleteCurrentFolderButton = new Element(xpath("//div[@id='toolbar']//div[@data-name='remove']"));
    private Element confirmDeleting = new Element(xpath("//button[@data-name='remove']"));
    private Element deleteNotify = new Element(xpath("//div[@class='notify']"));
    private Element searchLine = new Element(xpath("//input[@type='text']"));
    private Element searchButton = new Element(xpath("//button[@type='submit']"));
    private Element searchEmptyResult = new Element(xpath("//div[@class='b-search-results__empty']"));
    private Element confirmTrashNotify = new Element(xpath("//button[@data-name='close' "
            + "and contains(@class, 'btn_neighboring')]"));
    private Element uploadButton = new Element(xpath("//div[@id='cloud_toolbars']//div[@data-name='upload']"));
    private Element chooseFilesForUpload = new Element(xpath("//input[@type='file' and @class='drop-zone__input']"));
    private Element uploadedFinishedNotification = new Element(xpath("//div[@class='b-upload-panel']"));
    private String fileLink = "//div[contains(@data-id,'/%s') and @data-name='link']";
    private Element getLink = new Element(xpath("//a[@data-name='publish']"));
    private Element publishingForm = new Element(xpath("//div[@class='b-layer__placeholder']"));
    private Element lookAtButton = new Element(xpath("//div[@class='publishing__url__buttons']"
            + "//button[@data-name='popup']"));
    private Element moveButton = new Element(xpath("//button[@data-name='move']"));
    private Element uploadedFile = new Element(xpath("//div[@class='b-upload-panel']"
            + "//span[@class='b-filename__name']"));

    public boolean checkAuthorization() {
        if (authMenu.isDisplayed()) {
            return true;
        }
        return false;
    }

    public CloudHomePage createFolder(String folderName) {
        create.isDisplayed();
        create.click();
        createFolder.isDisplayed();
        createFolder.click();
        changeCreatedFolderName.isDisplayed();
        changeCreatedFolderName.clear();
        changeCreatedFolderName.type(folderName);
        createButton.click();
        new Element(xpath(String.format(findFile, folderName))).isDisplayed();
        return new CloudHomePage();
    }

    public Boolean checkFolderIsPresent(String folderName) {
        Element check = new Element(xpath(String.format(findFile, folderName)));
        return check != null;
    }

    public void deleteFolder(String folderName) {
        Element element = new Element(xpath(String.format(findFile, folderName)));
        element.click();
        deleteCurrentFolderButton.isDisplayed();
        deleteCurrentFolderButton.click();
        confirmDeleting.isDisplayed();
        confirmDeleting.click();
    }

    public boolean checkFolderIsNotPresent(String folderName) {
        confirmTrashNotify.isDisplayed();
        confirmTrashNotify.click();
        deleteNotify.isInvisible();
        searchLine.click();
        searchLine.type(folderName);
        searchButton.click();
        searchEmptyResult.isDisplayed();
        return searchEmptyResult != null;
    }

    public String uploadFile(File file) {
        uploadButton.isDisplayed();
        uploadButton.click();
        chooseFilesForUpload.type(file.getAbsolutePath());
        uploadedFinishedNotification.isDisplayed();
        return uploadedFile.getText();
    }

    public void dragAndDrop(String file, String folder) {
        Actions builder = new Actions(Browser.getInstance().getWrappedDriver());
        Element from = new Element(xpath(String.format(fileLink, file)));
        from.dragAndDropTo(xpath(String.format(fileLink, folder)));
        moveButton.isDisplayed();
        moveButton.click();
    }

    public void openFolder(String folder) {
        new Element(xpath(String.format(fileLink, folder)));
    }

    public void highlightTheElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Browser.getInstance().getWrappedDriver();
        WebElement webElement = element;
        js.executeScript("arguments[0].setAttribute('style', 'border: 4px solid red;');", webElement);
    }

    public String shareTheElement() {
        Actions builder = new Actions(Browser.getInstance().getWrappedDriver());
        String titleOfSharingElement = "Берег.jpg";
        //highlightTheElement(driver.findElement(title));
        Browser.getInstance().getWrappedDriver().findElement(xpath(String.format(fileLink, titleOfSharingElement)));
        builder
                .contextClick(Browser.getInstance().getWrappedDriver()
                        .findElement(xpath(String.format(fileLink, titleOfSharingElement))))
                .perform();
        getLink.isDisplayed();
        getLink.click();
        publishingForm.isDisplayed();
        return titleOfSharingElement;
    }

    public PublicPage checkSharedElement() {
        lookAtButton.isDisplayed();
        lookAtButton.click();
        return new PublicPage();
    }
}
