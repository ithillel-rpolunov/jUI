package jira.test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class JiraTest {

    protected WebDriver driver;

    @BeforeTest
    public void beforeTest(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void simpleTest() {

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();

        String eTitle = "Log in - JIRA";
        String aTitle = "";
 
        // запустить firefox и перейти по адресу
        driver.get("http://soft.it-hillel.com.ua:8080/login.jsp");
        // разворачивает окно браузера
        driver.manage().window().maximize();
        // получить значение у тайтла страницы
        aTitle = driver.getTitle();

        driver.findElement(By.xpath("//input[@id='login-form-username']")).sendKeys("login");

        // выполняем проверку
        assertEquals(aTitle, eTitle);
        assertEquals((driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/section/header/h1")).getText()),"Welcome to JIRA");
        assertEquals((driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[1]/fieldset/div[1]/label")).getText()),"Username");
        assertEquals((driver.findElement(By.xpath("//*[@id=\"passwordlabel\"]")).getText()),"Password");


        // закрываем окно браузера
//        driver.close();
	}

	@Test
	public void login(){

        String login = "r.polunov";
        String pass = "docent05041983";

        String eTitle = "System Dashboard - JIRA";
        String eProfile = "User profile for r.polunov";
        String eCreate = "Create";


        String aTitle;
        String aProfile;
        String aCreate;

//        driver.get("http://soft.it-hillel.com.ua:8080/login.jsp");
        driver.get("http://soft.it-hillel.com.ua:8080/secure/Dashboard.jspa");

        driver.manage().window().maximize();

//         заполняем поля
        driver.findElement(By.xpath("//*[@id=\"login-form-username\"]")).sendKeys(login);
        driver.findElement(By.xpath("//*[@id=\"login-form-password\"]")).sendKeys(pass);

//        клацаем на кнопку
//        driver.findElement(By.xpath("//*[@id=\"login-form-submit\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"login\"]")).click();
        aTitle = driver.getTitle();

        WebElement alt = driver.findElement(By.xpath("//*[@id=\"header-details-user-fullname\"]/span/span/img"));
        aProfile = alt.getAttribute("alt");

        aCreate = driver.findElement(By.xpath("//*[@id=\"create_link\"]")).getText();

        assertEquals(aTitle, eTitle);
        assertEquals(eProfile, aProfile );
        assertEquals(eCreate, aCreate);

    }

    @Test   (dependsOnMethods = {"login"})
    public void creatIssue(){

        //находим кнопку Create и нажимаем
        driver.findElement(By.xpath("//*[@id=\"create_link\"]")).click();
//        driver.findElement(By.xpath("//div[@id='project-single-select']/span")).click();
//        "//*[@id=\"project-options\"]"
//        driver.findElement(By.linkText("QAAutomation2 (QAAUT)")).click();

//        WebElement projectDropDown = driver.findElement(By.xpath("//*[@id=\"project-field\"]"));
//        new Select(projectDropDown).selectByVisibleText("QAAutomation2 (QAAUT)");

//        Select projectDropDown = new Select(driver.findElement(By.xpath("//*[@id=\"issuetype-single-select\"]/span")));
//        projectDropDown.selectByVisibleText("QAAutomation2 (QAAUT)");

//        driver.findElement(By.id("summary")).sendKeys("summary");

        driver.findElement(By.xpath("//*[@id=\"project-field\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"project-field\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"project-field\"]")).sendKeys("QAAUT", Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, 2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"issuetype-field\"]")));

        driver.findElement(By.xpath("//*[@id=\"issuetype-field\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"issuetype-field\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"issuetype-field\"]")).sendKeys("Task",Keys.ENTER);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("summary")));
        driver.findElement(By.id("summary")).sendKeys("summary");

        driver.findElement(By.id("reporter-field")).clear();
        driver.findElement(By.id("reporter-field")).sendKeys("r.polunov");

        driver.findElement(By.id("description")).clear();
        driver.findElement(By.id("description")).sendKeys("Desc");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"priority-field\"]")));
        driver.findElement(By.xpath("//*[@id=\"priority-field\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"priority-field\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"priority-field\"]")).sendKeys("Lowest",Keys.ENTER);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"labels-textarea\"]")));
        driver.findElement(By.xpath("//*[@id=\"labels-textarea\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"labels-textarea\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"labels-textarea\"]")).sendKeys("JiraUISelenium",Keys.ENTER);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"assignee-field\"]")));
        driver.findElement(By.xpath("//*[@id=\"assignee-field\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"assignee-field\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"assignee-field\"]")).sendKeys("r.polunov",Keys.ENTER);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"create-issue-submit\"]")));
        driver.findElement(By.xpath("//*[@id=\"create-issue-submit\"]")).click();

        String createIssue = driver.findElement(By.xpath("//*[@id=\"aui-flag-container\"]/div/div/a")).getAttribute("data-issue-key");
        System.out.println(createIssue);

        assertTrue(createIssue.contains("QAAUT-"));

    }

}

