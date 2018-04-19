package com.epam.tat.services.cloud;

import com.epam.tat.pages.cloud.CloudHomePage;
import com.epam.tat.pages.cloud.PublicPage;

import java.io.File;

public class CloudService {

    public void createFolder(String folderName) {
        new CloudHomePage().createFolder(folderName);
    }

    public boolean checkCreatedFolder(String folderName) {
        CloudHomePage cloudHomePage = new CloudHomePage();
        return  cloudHomePage.checkFolderIsPresent(folderName);
    }

    public void createAndDeleteFolder(String folderName) {
        CloudHomePage cloudHomePage = new CloudHomePage();
        cloudHomePage.createFolder(folderName)
                .deleteFolder(folderName);
    }

    public boolean checkFolder(String folderName) {
        return new CloudHomePage()
                .checkFolderIsNotPresent(folderName);
    }

    public String uploadFile(File file) {
        return new CloudHomePage().uploadFile(file);
    }

    public void openFolder(String folderName) {
        new CloudHomePage().openFolder(folderName);
    }

    public void dragAndDropFile(String folderName, String fileName) {
        createFolder(folderName);
        new CloudHomePage().dragAndDrop(fileName, folderName);
        openFolder(folderName);
    }

    public String shareTheElement() {
        return new CloudHomePage().shareTheElement();
    }

    public void checkSharedElement() {
        new CloudHomePage().checkSharedElement();
    }

    public String titleOfSharedElement() {
        return new PublicPage().getTitleText();
    }
}
