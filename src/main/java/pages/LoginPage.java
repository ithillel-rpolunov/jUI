package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Vasya on 27.10.2016.
 */
public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void enterLogin(String login){
        driver.findElement(By.xpath("//*[@id=\"login-form-username\"]")).sendKeys(login);
    }

    public void enterPass(String pass){
        driver.findElement(By.xpath("//*[@id=\"login-form-password\"]")).sendKeys(pass);
    }

    public void clickLogin(){
        driver.findElement(By.xpath("//*[@id=\"login\"]")).click();
    }


}
