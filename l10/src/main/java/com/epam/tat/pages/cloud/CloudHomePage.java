package com.epam.tat.pages.cloud;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class CloudHomePage {

    public static final int TIME_OUT = 40;

    private By authMenuLocator = By.id("PH_user-email");
    private By createLocator = By.xpath("//div[@id='cloud_toolbars']//div[@data-group='create']"
            + "//div[@data-mnemo='ctrl']");
    private By createFolderLocator = By.cssSelector("div[class='b-dropdown__list']:not([style*='none'])"
            + " a[data-name='folder']");
    private By changeCreatedFolderName = By.cssSelector(".layer__input");
    private String findFile = "//div[@class='b-filename__spacer' and contains(text(), '%s')]/parent::div";
    private By createButton = By.xpath("//button[@data-name='add']");
    private By deleteCurrentFolderButton = By.xpath("//div[@id='toolbar']//div[@data-name='remove']");
    private By confirmDeleting = By.xpath("//button[@data-name='remove']");
    private By deleteNotify = By.xpath("//div[@class='notify']");
    private By searchLine = By.xpath("//input[@type='text']");
    private By searchButton = By.xpath("//button[@type='submit']");
    private By searchEmptyResult = By.xpath("//div[@class='b-search-results__empty']");
    private By confirmTrashNotify = By.xpath("//button[@data-name='close' and contains(@class, 'btn_neighboring')]");
    private By uploadButton = By.xpath("//div[@id='cloud_toolbars']//div[@data-name='upload']");
    private By chooseFilesForUpload = By.xpath("//input[@type='file' and @class='drop-zone__input']");
    private By uploadedFinishedNotification = By.xpath("//div[@class='b-upload-panel']");
    private String fileLink = "//div[contains(@data-id,'/%s') and @data-name='link']";
    private By getLink = By.xpath("//a[@data-name='publish']");
    private By publishingForm = By.xpath("//div[@class='b-layer__placeholder']");
    private By lookAtButton = By.xpath("//div[@class='publishing__url__buttons']//button[@data-name='popup']");
    private By moveButton = By.xpath("//button[@data-name='move']");
    private By uploadedFile = By.xpath("//div[@class='b-upload-panel']//span[@class='b-filename__name']");

    private WebDriver driver;

    public CloudHomePage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement visibilityWait(By locator) {
        return new WebDriverWait(driver, TIME_OUT).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public String checkAuthorization() {
        visibilityWait(authMenuLocator);
        return driver.findElement(authMenuLocator).getText();
    }

    public void createFolder(String folderName) {
        visibilityWait(createLocator);
        driver.findElement(createLocator).click();
        visibilityWait(createFolderLocator);
        driver.findElement(createFolderLocator).click();
        visibilityWait(changeCreatedFolderName);
        driver.findElement(changeCreatedFolderName).clear();
        driver.findElement(changeCreatedFolderName).sendKeys(folderName);
        driver.findElement(createButton).click();
        visibilityWait(By.xpath(String.format(findFile, folderName)));
    }

    public Boolean checkFolderIsPresent(String folderName) {
        visibilityWait(By.xpath(String.format(findFile, folderName)));
        return driver.findElement(By.xpath(String.format(findFile, folderName))) != null;
    }

    public void deleteFolder(String folderName) {
        visibilityWait(By.xpath(String.format(findFile, folderName)));
        driver.findElement(By.xpath(String.format(findFile, folderName))).click();
        visibilityWait(deleteCurrentFolderButton);
        driver.findElement(deleteCurrentFolderButton).click();
        visibilityWait(confirmDeleting);
        driver.findElement(confirmDeleting).click();
    }

    public boolean checkFolderIsNotPresent(String folderName) {
        visibilityWait(confirmTrashNotify);
        driver.findElement(confirmTrashNotify).click();
        new WebDriverWait(driver, TIME_OUT).until(ExpectedConditions.invisibilityOfElementLocated(deleteNotify));
        driver.findElement(searchLine).click();
        driver.findElement(searchLine).sendKeys(folderName);
        driver.findElement(searchButton).click();
        visibilityWait(searchEmptyResult);
        return driver.findElement(searchEmptyResult) != null;
    }

    public String uploadFile(String filePath) {
        File file = new File(filePath);
        visibilityWait(uploadButton);
        driver.findElement(uploadButton).click();
        driver.findElement(chooseFilesForUpload).sendKeys(file.getAbsolutePath());
        visibilityWait(uploadedFinishedNotification);
        return driver.findElement(uploadedFile).getText();
    }

    public void dragAndDrop(String file, String folder) {
        Actions builder = new Actions(driver);
        WebElement from = driver.findElement(By.xpath(String.format(fileLink, file)));
        WebElement to = driver.findElement(By.xpath(String.format(fileLink, folder)));
        Action dragAndDrop = builder.clickAndHold(from)
                .moveToElement(to)
                .release(to)
                .build();
        dragAndDrop.perform();
        visibilityWait(moveButton);
        driver.findElement(moveButton).click();
    }

    public void openFolder(String folder) {
        driver.findElement(By.xpath(String.format(fileLink, folder)));
    }

    public void highlightTheElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement webElement = element;
        js.executeScript("arguments[0].setAttribute('style', 'border: 4px solid red;');", webElement);
    }

    public String shareTheElement() {
        Actions builder = new Actions(driver);
        String titleOfSharingElement = "Берег.jpg";
        By byElement = By.xpath(String.format(fileLink, titleOfSharingElement));
        visibilityWait(byElement);
        highlightTheElement(driver.findElement(byElement));
        WebElement element = driver.findElement(byElement);
        builder.contextClick(element)
                .perform();
        visibilityWait(getLink);
        driver.findElement(getLink).click();
        visibilityWait(publishingForm);
        return titleOfSharingElement;
    }

    public PublicPage checkSharedElement() {
        visibilityWait(lookAtButton);
        driver.findElement(lookAtButton).click();
        return new PublicPage(driver);
    }
}
