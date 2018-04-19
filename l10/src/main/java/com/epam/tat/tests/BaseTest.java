package com.epam.tat.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static final String EMAIL = "juicy_j16@mail.ru";
    public static final String CORRECT_PASS = "qazxsw123";
    public static final String INVALID_CRED = "Неверное имя пользователя или пароль";

    public static final String FILE_PATH = "E:/1.txt";

    public static final String MAIN_PAGE = "https://mail.ru/";

    public static final int TIME_OUT = 50;
    public static final int SCRIPT_TIME_OUT = 20;

    protected WebDriver driver;

    @BeforeMethod
    public void setUpDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(TIME_OUT, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(SCRIPT_TIME_OUT, TimeUnit.SECONDS);
        driver.get(MAIN_PAGE);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
