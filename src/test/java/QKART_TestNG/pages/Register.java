package QKART_TestNG.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.sql.Timestamp;  


public class Register {

    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app/register";
    public String lastGeneratedUsername = "";

    @FindBy(id="username")
    WebElement username_text_box;
    @FindBy(id="password")
    WebElement password_text_box;
    @FindBy(id="confirmPassword")
    WebElement confirm_password_text_box;
    @FindBy(className="button")
    WebElement register_button;

    public Register(RemoteWebDriver driver){
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);
    }

    public void navigateToRegisterPage(){
        if(!driver.getCurrentUrl().equals(this.url)){
            driver.get(this.url);
        }
    }
    
    public Boolean registerUser(String username, String password, boolean makeUsernameDynamic) throws InterruptedException{
        
        Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
        String test_data_username = username;
        
        if(makeUsernameDynamic)
            test_data_username = username + "_" + String.valueOf(timestamp.getTime());
        else
            test_data_username = username;

        String test_data_password = password;
        //WebElement username_text_box = driver.findElement(By.id("username"));
        //WebElement password_text_box = driver.findElement(By.id("password"));
        //WebElement confirm_password_text_box = driver.findElement(By.id("confirmPassword"));
        //WebElement register_button = driver.findElement(By.className("button"));

        System.out.println("Registering with username: " + test_data_username + test_data_password);
        username_text_box.sendKeys(test_data_username);
        password_text_box.sendKeys(test_data_password);
        confirm_password_text_box.sendKeys(test_data_password);
        register_button.click();
        this.lastGeneratedUsername = test_data_username;
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlToBe("https://crio-qkart-frontend-qa.vercel.app/login"),
                ExpectedConditions.visibilityOfElementLocated(By.xpath(" //*[@id='notistack-snackbar']"))
            )
        );
        return this.driver.getCurrentUrl().endsWith("/login");
    }
}
