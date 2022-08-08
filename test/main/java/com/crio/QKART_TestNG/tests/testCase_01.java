package com.crio.QKART_TestNG.tests;

import com.crio.QKART_TestNG.pages.Login;
import com.crio.QKART_TestNG.pages.Register;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import static org.testng.Assert.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;



public class testCase_01 {

 static WebDriver driver = new ChromeDriver();
    static String LastGeneratedName__;

    @BeforeSuite(alwaysRun = true)
    public static void createDriver() throws MalformedURLException {
        // IMPORTANT!: Enter the Driver Location here
        
// Code to Launch Browser using Zalenium in Crio workspace
final DesiredCapabilities capabilities = new DesiredCapabilities();
capabilities.setBrowserName(BrowserType.CHROME);
WebDriver driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"),
        capabilities);
    }

    public static void logStatus(String type, String message, String status) {

        System.out.println(String.format("%s |  %s  |  %s | %s", String.valueOf(java.time.LocalDateTime.now()), type,
                message, status));

    }

    public void takeScreenshot(String screenshotType, String Description) {
        try {
            File theDir = new File("/screenshots");
            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            String timestamp = String.valueOf(java.time.LocalDateTime.now());
            String fileName = String.format("screenshot_%s_%s_%s.png", timestamp, screenshotType, Description);
            TakesScreenshot scrShot = ((TakesScreenshot) driver);
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile = new File("screenshots/" + fileName);
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void registerNewser() throws InterruptedException
    {   SoftAssert sa = new SoftAssert();
        
        Register registration = new Register(driver);
        registration.navigateToRegisterPage();
        sa.assertTrue(registration.registerUser("testUser", "abc@123", true),"Failed to create a new user ");
        LastGeneratedName__ = registration.lastGeneratedUsername;
    }

    @Test(description = "Verify if new user can be created and logged in ")
    public static void TestCase01() throws InterruptedException {
        registerNewser();
        String  lastGeneratedUserName = LastGeneratedName__;
        Login login = new Login(driver);
        login.navigateToLoginPage();
        boolean status = login.PerformLogin(lastGeneratedUserName, "abc@123");
        assertTrue(status);
    }

    @AfterSuite
    public static void quitDriver()
    {
        driver.quit();
    }


}


