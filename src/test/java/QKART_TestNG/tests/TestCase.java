package QKART_TestNG.tests;

import QKART_TestNG.pages.Login;
import QKART_TestNG.pages.Register;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.Assert;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestCase {
    
    static RemoteWebDriver driver;

    public static void logStatus(String type, String message, String status) {
        System.out.println(String.format("%s |  %s  |  %s | %s",
                String.valueOf(java.time.LocalDateTime.now()), type, message, status));
    }

    // Iinitialize webdriver for our Unit Tests
    @BeforeSuite(enabled = true, alwaysRun = true)
    public static void createDriver() throws MalformedURLException {
        logStatus("driver", "Initializing driver", "Started");
        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
        logStatus("driver", "Initializing driver", "Success");
    }

    @Test(description = "For testing user registration & login flow", enabled=true)
    public static void testCase() throws InterruptedException{
        Register register = new Register(driver);
        register.navigateToRegisterPage(); 
        Assert.assertTrue(register.registerUser("username@123", "password@123", true));
        String uniqueUsername = register.lastGeneratedUsername;
        Login login = new Login(driver);
        login.navigateToLoginPage();
        login.performLogin(uniqueUsername, "password@123");
        Assert.assertTrue(login.verifyUserLoggedIn(uniqueUsername));

    
    }

}
