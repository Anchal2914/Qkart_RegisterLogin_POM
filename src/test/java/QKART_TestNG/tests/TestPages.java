package QKART_TestNG.tests;

import QKART_TestNG.pages.Login;
import QKART_TestNG.pages.Register;
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
		System.out.println(String.format("%s |  %s  |  %s | %s",
				String.valueOf(java.time.LocalDateTime.now()), type, message, status));
	}

	// Iinitialize webdriver for our Unit Tests
	@BeforeClass(alwaysRun = true, enabled = false)
	public static void createDriver() throws MalformedURLException {
		logStatus("driver", "Initializing driver", "Started");
		final DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(BrowserType.CHROME);
		driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
		logStatus("driver", "Initializing driver", "Success");
	}



	// TODO: Use the following to test --navigateToRegisterPage()-- method

	@Test(description = "Verify functionality of - navigate to register page", enabled = false)
	public static void testRegister_navigateToRegisterPage() {
		//Assertion assertion = new Assertion();
		logStatus("Page test", "navigation to register page", "started");
		try {
			Register register = new Register(driver);
			register.navigateToRegisterPage();
			//assertion.assertTrue(driver.getCurrentUrl()=="https://crio-qkart-frontend-qa.vercel.app/register");
			logStatus("Page test", "navigation to register page", "success");
		} catch (Exception e) {
			logStatus("Page test", "navigation to register page", "failed");
			e.printStackTrace();
		}
	}


	// TODO: Implement Rest of Unit Tests Here-

	@Test(description = "Verify functionality of - register user", enabled = false)
	public static void testRegister_registerUser() {
		logStatus("Page test", "register user", "started");
		try {
			Register register = new Register(driver);
			register.navigateToRegisterPage();
			Boolean status = register.registerUser("testcheck@123", "abc@123", true);
			if(!status){
				throw new Exception("Not Registerted"); 
			}
			logStatus("Page test", "register user", "success");
		} catch (Exception e) {
			logStatus("Page test", "register user", "failed");
			e.printStackTrace();
		}
	}

	@Test(description = "Verify functionality of - navigate to login page", enabled = false)
	public static void testLogin_navigateToLoginPage() {
		//Assertion assertion = new Assertion();
		logStatus("Page test", "navigation to login page", "started");
		try {
			Login login = new Login(driver);
			login.navigateToLoginPage();
			logStatus("Page test", "navigation to login page", "success");
		} catch (Exception e) {
			logStatus("Page test", "navigation to login page", "failed");
			e.printStackTrace();
		}
	}

	@Test(description = "Verify functionality of - perform log in", enabled = false)
	public static void testLogin_performLogin() {
		try {
			logStatus("Page test", "perform log in", "started");
			Register register = new Register(driver);
			register.navigateToRegisterPage();
			register.registerUser("testcheck@123", "abc@123", true);
			String username = register.lastGeneratedUsername;
			Thread.sleep(3000);
			Login login = new Login(driver);
			login.navigateToLoginPage();
			Boolean status = login.performLogin(username, "abc@123");
			if(!status){
				throw new Exception("Not Registerted"); 
			}
			logStatus("Page test", "perform log in", "success");
		} catch (Exception e) {
			logStatus("Page test", "perform log in", "failed");
			e.printStackTrace();
		}
	}

	@Test(description = "Verify functionality of - verify user logged in", enabled = false)
	public static void testLogin_verifyUserLoggedIn() {
		try {
			logStatus("Page test", "verify user logged in", "started");
			Register register = new Register(driver);
			register.navigateToRegisterPage();
			register.registerUser("testcheck@123", "abc@123", true);
			String username = register.lastGeneratedUsername;
			Thread.sleep(3000);
			Login login = new Login(driver);
			login.navigateToLoginPage();
			login.performLogin(username, "abc@123");
			Boolean status = login.verifyUserLoggedIn(username);
			if(!status){
				throw new Exception("Not Logged in"); 
			}
			logStatus("Page test", "verify user logged in", "success");
		} catch (Exception e) {
			logStatus("Page test", "verify user logged in", "failed");
			e.printStackTrace();
		}
	}

	// Quit webdriver after Unit Tests
	@AfterClass(enabled = true)
	public static void quitDriver() throws MalformedURLException {
		driver.close();
		driver.quit();
		logStatus("driver", "Quitting driver", "Success");
	}

}
