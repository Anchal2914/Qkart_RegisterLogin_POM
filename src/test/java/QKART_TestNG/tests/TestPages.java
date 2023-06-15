package QKART_TestNG.tests;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestPages {

    static RemoteWebDriver driver;

    // Method to help us log our Unit Tests
	public static void logStatus(String type, String message, String status) {
    	System.out.println(String.format("%s |  %s  |  %s | %s", String.valueOf(java.time.LocalDateTime.now()), type,
            	message, status));
	}
    // Iinitialize webdriver for our Unit Tests
	@BeforeClass(alwaysRun = true, enabled = true)
	public static void createDriver() throws MalformedURLException{
    	logStatus("driver","Initializing driver","Started");
    	final DesiredCapabilities capabilities = new DesiredCapabilities();
    	capabilities.setBrowserName(BrowserType.CHROME);
    	driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
    	logStatus("driver","Initializing driver","Success");   	 
	}

    // Quit webdriver after Unit Tests
    @AfterClass(enabled = true)
	public static void quitDriver() throws MalformedURLException{
    	driver.close();
        driver.quit();
    	logStatus("driver","Quitting driver","Success");   	 
	}

    // TODO: Use the following to test --navigateToRegisterPage()-- method 

    @Test(description = "Verify functionality of - navigate to register page", enabled = true)
	public static void testRegister_navigateToRegisterPage() {
    	logStatus("Page test", "navigation to register page", "started");
    	try {
        	Register register = new Register(driver);
        	register.navigateToRegisterPage();
        	logStatus("Page test", "navigation to register page", "success");
    	} catch (Exception e) {
        	logStatus("Page test", "navigation to register page", "failed");
        	e.printStackTrace();
    	}
	}


    // TODO: Implement Rest of Unit Tests Here-

    

}
