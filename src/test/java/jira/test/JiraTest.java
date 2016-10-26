package jira.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

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

//        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
//        WebDriver driver = new ChromeDriver();

//        driver.get("http://soft.it-hillel.com.ua:8080/secure/Dashboard.jspa");
        //находим кнопку Create и нажимаем
        driver.findElement(By.xpath("//*[@id=\"create_link\"]")).click();
//        driver.findElement(By.xpath("//div[@id='project-single-select']/span")).click();
//        "//*[@id=\"project-options\"]"
//        driver.findElement(By.linkText("QAAutomation2 (QAAUT)")).click();

//        WebElement projectDropDown = driver.findElement(By.xpath("//*[@id=\"project-field\"]"));
//        new Select(projectDropDown).selectByVisibleText("QAAutomation2 (QAAUT)");

//        Select projectDropDown = new Select(driver.findElement(By.xpath("\"//div[@id='project-single-select']")));
//        projectDropDown.selectByVisibleText("QAAutomation2 (QAAUT)");

        new Select(driver.findElement(By.xpath("//*[@id=\"project-field\"]"))).selectByIndex(2);

//        driver.findElement(By.xpath("(//a[contains(text(),'QAAutomation2 (QAAUT)')])")).click();
//        driver.findElement(By.xpath("//div[@id='issuetype-single-select']/span")).click();
//        driver.findElement(By.linkText("Bug")).click();
//        driver.findElement(By.xpath("//div[@id='issuetype-single-select']/span")).click();
//        driver.findElement(By.id("summary")).click();
//        driver.findElement(By.id("summary")).clear();
//        driver.findElement(By.id("summary")).sendKeys("summary");
//        driver.findElement(By.id("reporter-field")).clear();
//        driver.findElement(By.id("reporter-field")).sendKeys("r.polunov_");
//        driver.findElement(By.id("description")).clear();
//        driver.findElement(By.id("description")).sendKeys("Desc");
//        driver.findElement(By.xpath("//div[@id='priority-single-select']/span")).click();
//        driver.findElement(By.linkText("High")).click();
//        driver.findElement(By.id("labels-textarea")).click();
//        driver.findElement(By.id("labels-textarea")).clear();
//        driver.findElement(By.id("labels-textarea")).sendKeys("lable");
//        driver.findElement(By.id("environment")).click();
//        driver.findElement(By.linkText("Cancel")).clear();
//        driver.findElement(By.linkText("Cancel")).sendKeys("Envi");
//        driver.findElement(By.id("assignee-field")).click();
//        driver.findElement(By.id("assignee-field")).clear();
//        driver.findElement(By.id("assignee-field")).sendKeys("r.polunov");
//        driver.findElement(By.linkText("Cancel")).click();



//        String project = "";
//        String IissueType = "";
//        String summary = "";
//        String reporterRequired = "";
//        String description = "";
//        String priority = "";
//        String labels = "";
//        String assignee = "";

//        клацаем на кнонопку "Create"
//        driver.findElement(By.xpath("//*[@id=\"create_link\"]"));

//        WebElement projectDropDown = driver.findElement(By.xpath("//*[@id=\"project-field\"]"));
//        new Select(projectDropDown).selectByVisibleText("123 (QWE)");

    }


    @Test
    public void testUntitled2() throws Exception {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver.get("http://soft.it-hillel.com.ua:8080/secure/Dashboard.jspa");
        driver.findElement(By.id("create_link")).click();
//        for (int second = 0;; second++) {
//            if (second >= 60) fail("timeout");
//            try { if (isElementPresent(By.id("create-issue-submit"))) break; } catch (Exception e) {}
//            Thread.sleep(1000);
//        }

        Thread.sleep(2000);

        driver.findElement(By.xpath("//div[@id='project-single-select']/span")).click();
        driver.findElement(By.linkText("123 (QWE)")).click();
        driver.findElement(By.xpath("(//a[contains(text(),'123 (QWE)')])[2]")).click();
        driver.findElement(By.xpath("//div[@id='issuetype-single-select']/span")).click();
        driver.findElement(By.linkText("Bug")).click();
        driver.findElement(By.xpath("//div[@id='issuetype-single-select']/span")).click();
        driver.findElement(By.id("summary")).click();
        driver.findElement(By.id("summary")).clear();
        driver.findElement(By.id("summary")).sendKeys("summary");
        driver.findElement(By.id("reporter-field")).clear();
        driver.findElement(By.id("reporter-field")).sendKeys("r.polunov_");
        driver.findElement(By.id("description")).clear();
        driver.findElement(By.id("description")).sendKeys("Desc");
        driver.findElement(By.xpath("//div[@id='priority-single-select']/span")).click();
        driver.findElement(By.linkText("High")).click();
        driver.findElement(By.id("labels-textarea")).click();
        driver.findElement(By.id("labels-textarea")).clear();
        driver.findElement(By.id("labels-textarea")).sendKeys("lable");
        driver.findElement(By.id("environment")).click();
        driver.findElement(By.linkText("Cancel")).clear();
        driver.findElement(By.linkText("Cancel")).sendKeys("Envi");
        driver.findElement(By.id("assignee-field")).click();
        driver.findElement(By.id("assignee-field")).clear();
        driver.findElement(By.id("assignee-field")).sendKeys("r.polunov");
        driver.findElement(By.linkText("Cancel")).click();
        }

}

