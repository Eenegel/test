package com.epam.tat.framework.ui;

import org.openqa.selenium.By;

public class Element {

    private By locator;

    public Element(By locator) {
        this.locator = locator;
    }

    public void click() {
        Browser.getInstance().click(locator);
    }

    public void type(String text) {
        Browser.getInstance().type(locator, text);
    }

    public String getText() {
        return Browser.getInstance().getText(locator);
    }

    public boolean isDisplayed() {
        return Browser.getInstance().isVisible(locator);
    }

    public void switchToDefaultContent() {
        Browser.getInstance().getWrappedDriver().switchTo().defaultContent();
    }

    public void switchToFrame() {
        Browser.getInstance().switchTo(locator);
    }

    public void clear() {
        Browser.getInstance().clear(locator);
    }

    public boolean isClickable() {
        return Browser.getInstance().isClickable(locator);
    }

    public boolean isInvisible() {
        return Browser.getInstance().isInvisible(locator);
    }

    public String getAttribute(String text) {
        return Browser.getInstance().getAttribute(locator, text);
    }

    public String getCssValue(String cssValue) {
        return Browser.getInstance().getWrappedDriver().findElement(locator).getCssValue(cssValue);
    }

    public void dragAndDropTo(By to) {
        Browser.getInstance().dragAndDrop(Browser.getInstance().getWrappedDriver().findElement(locator),
                Browser.getInstance().getWrappedDriver().findElement(to));

    }
}
