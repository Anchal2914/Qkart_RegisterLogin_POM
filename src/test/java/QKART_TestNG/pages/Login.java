package QKART_TestNG.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class Login {

    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app/login";

    @FindBy(id="username")
    WebElement username_txt_box;
    @FindBy(id="password")
    WebElement password_txt_box;
    @FindBy(className="button")
    WebElement login_button;
    @FindBy(className="username-text")
    WebElement username_label;
    
    public Login(RemoteWebDriver driver){
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);
    }

    public void navigateToLoginPage(){
        if(!driver.getCurrentUrl().equals(this.url)){
            driver.get(this.url);
        }
    }

    public Boolean performLogin(String username, String password) throws InterruptedException{
        // WebElement username_txt_box = driver.findElement(By.id("username"));
        // WebElement password_txt_box = driver.findElement(By.id("password"));
        // WebElement login_button = driver.findElement(By.className("button"));

        System.out.println("logging in with username: " + username + "pwd: " + password);
        username_txt_box.sendKeys(username);
        password_txt_box.sendKeys(password);
        login_button.click();

        FluentWait<RemoteWebDriver> wait = new FluentWait<>(driver)
                .withTimeout((Duration.ofSeconds(30)))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(Exception.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='root']/div/div/div[1]/div[2]/div/input")));
        return this.verifyUserLoggedIn(username);
    }

    public Boolean verifyUserLoggedIn(String username){
        try{
            //WebElement username_label = driver.findElement(By.className("username-text"));
            return username_label.getText().equals(username);
        } catch(Exception e) {
        return false;
        }
    }
    
}
