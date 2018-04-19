package com.epam.tat.services.cloud;

import com.epam.tat.framework.logging.Log;
import com.epam.tat.pages.cloud.CloudHomePage;
import com.epam.tat.pages.cloud.PublicPage;

import java.io.File;

public class CloudService {

    public void createFolder(String folderName) {
        Log.debug(String.format("Trying to create folder with name: %s",
                folderName));
        new CloudHomePage().createFolder(folderName);
    }

    public boolean checkCreatedFolder(String folderName) {
        CloudHomePage cloudHomePage = new CloudHomePage();
        return  cloudHomePage.checkFolderIsPresent(folderName);
    }

    public void createAndDeleteFolder(String folderName) {
        createFolder(folderName);
        Log.debug(String.format("Trying to delete folder with name: %s",
                folderName));
        new CloudHomePage().deleteFolder(folderName);
    }

    public boolean checkFolder(String folderName) {
        return new CloudHomePage()
                .checkFolderIsNotPresent(folderName);
    }

    public String uploadFile(File file) {
        Log.debug(String.format("Trying to upload file: %s", file));
        return new CloudHomePage().uploadFile(file);
    }

    public void openFolder(String folderName) {
        Log.debug(String.format("Trying to open folder: %s", folderName));
        new CloudHomePage().openFolder(folderName);
    }

    public void dragAndDropFile(String folderName, String fileName) {
        createFolder(folderName);
        Log.debug(String.format("Trying to drag and drop file %s to %s", fileName, folderName));
        new CloudHomePage().dragAndDrop(fileName, folderName);
        openFolder(folderName);
    }

    public String shareTheElement() {
        Log.debug(String.format("Trying to share the element"));
        return new CloudHomePage().shareTheElement();
    }

    public void checkSharedElement() {
        new CloudHomePage().checkSharedElement();
    }

    public String titleOfSharedElement() {
        return new PublicPage().getTitleText();
    }
}
