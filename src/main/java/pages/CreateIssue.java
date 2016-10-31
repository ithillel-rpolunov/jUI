package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Vasya on 27.10.2016.
 */
public class CreateIssue {

    private WebDriver driver;
//    WebDriverWait wait = new WebDriverWait(driver, 2000);

    public CreateIssue(WebDriver driver){
        this.driver = driver;
    }

    public void clickCreateIssue(String button){
        driver.findElement(By.xpath("//*[@id=\"" + button + "\"]")).click();
    }

    public void clickButtonFullXpath(String button){
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath(button)).click();
    }

//create_link
    public void fieldField(String field, String value){
        driver.findElement(By.id(field)).clear();
        driver.findElement(By.id(field)).sendKeys(value);
    }

    public void fieldFieldKeyboard(String field, String value){
        driver.findElement(By.id(field)).clear();
        driver.findElement(By.id(field)).sendKeys(value, Keys.ALT + "s");
    }

    public void fieldFieldplusEnter(String field, String value){
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.id(field)).clear();
        driver.findElement(By.id(field)).sendKeys(value, Keys.ENTER);
    }

    public void univerlaChouse(String field, String value){
//    WebDriverWait wait = new WebDriverWait(driver, 2000);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"" + field + "\"]")));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//*[@id=\"" + field + "\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"" + field + "\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"" + field + "\"]")).sendKeys(value,Keys.ENTER);
    }

    public void univerlaChouseEdit(String field, String value){
//    WebDriverWait wait = new WebDriverWait(driver, 2000);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"" + field + "\"]")));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


        driver.findElement(By.xpath("//*[@id=\"" + field + "\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"" + field + "\"]")).clear();

        field = "priority-field";
        driver.findElement(By.xpath("//*[@id=\"" + field + "\"]")).sendKeys(value, Keys.TAB);
        //*[@id="priority-field"]
    }




    public void universalFieldEditor(String field, String value, String button){
        clickCreateIssue(field);
        fieldFieldplusEnter(field, value);

//        clickButtonFullXpath(button);
        clickButtonFullXpath("//*[@id=\"descriptionmodule\"]");
        clickButtonFullXpath(button);
    }

    public void changeTypeKeyboard(String mainContainer, String insideContainer, String value){
        driver.findElement(By.xpath("//*[@id=\"" + mainContainer + "\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"" + insideContainer + "\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"" + insideContainer + "\"]")).sendKeys(value, Keys.ALT+"S");
    }

    public void waiter(String fieldName){
        while(driver.findElement(By.xpath("//*[@id=\"" + fieldName + "\"]")).getText().equalsIgnoreCase(""))
        {
            System.out.println("waiting because field are empty");
        }
    }

    public void waiterIssue(String fieldName){
        while(!driver.findElement(By.xpath("//*[@id=\"" + fieldName + "\"]")).getText().equalsIgnoreCase("Epic"))
        {
            System.out.println("waiting because field are empty");
        }
    }







}
