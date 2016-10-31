package jira.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.CreateIssue;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

/**
 * Created by Vasya on 29.10.2016.
 */
public class JiraPageObjectTest {


    protected WebDriver driver;

    @BeforeTest
    public void beforeTest(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
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

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterLogin(login);
        loginPage.enterPass(pass);
        loginPage.clickLogin();

        aTitle = driver.getTitle();

        WebElement alt = driver.findElement(By.xpath("//*[@id=\"header-details-user-fullname\"]/span/span/img"));
        aProfile = alt.getAttribute("alt");

        aCreate = driver.findElement(By.xpath("//*[@id=\"create_link\"]")).getText();

        assertEquals(aTitle, eTitle);
        assertEquals(eProfile, aProfile );
        assertEquals(eCreate, aCreate);
           driver.close();

    }



    @Test   (dependsOnMethods = {"login"})
    public void creatIssueWithPageObject(){

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        CreateIssue createIssue = new CreateIssue(driver);

        createIssue.clickCreateIssue("create_link");

        createIssue.univerlaChouse("project-field", "QAAUT");

        WebElement element = driver.findElement(By.xpath("//*[@id=\"issuetype-field\"]"));
        createIssue.univerlaChouse("issuetype-field", "Task");

        element = driver.findElement(By.id("summary"));
        createIssue.fieldField("summary","test for summary field");

        createIssue.fieldField("reporter-field","r.polunov");

        createIssue.fieldField("description","Desc");

        createIssue.univerlaChouse("priority-field", "Lowest");

        createIssue.univerlaChouse("labels-textarea", "JiraUISelenium");

        createIssue.univerlaChouse("assignee-field", "r.polunov");

        createIssue.clickCreateIssue("create-issue-submit");

        String createIssue1 = driver.findElement(By.xpath("//*[@id=\"aui-flag-container\"]/div/div/a")).getAttribute("data-issue-key");
        System.out.println(createIssue1);

        assertTrue(createIssue1.contains("QAAUT-"));

        driver.close();

    }


    @Test (dependsOnMethods = "login")
    public void updateReporter(){
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);

        driver.get("http://soft.it-hillel.com.ua:8080/browse/QAAUT-1209");

        CreateIssue createIssue = new CreateIssue(driver);

        createIssue.clickCreateIssue("reporter-val");

        createIssue.fieldFieldplusEnter("reporter-field", "test");
        createIssue.clickButtonFullXpath("//*[@id=\"descriptionmodule\"]");

        createIssue.waiter("reporter-val");

        String assigneVal = driver.findElement(By.xpath("//*[@id=\"assignee-val\"]")).getText();
        String reportVal = driver.findElement(By.xpath("//*[@id=\"reporter-val\"]")).getText();

//        System.out.println("assigneVal " + assigneVal);
//        System.out.println("reportVal " + reportVal);

        assertNotEquals(assigneVal,reportVal);
//        assertEquals(reportVal, "r.polunov");
        assertEquals(reportVal, "Test");
        driver.close();
    }

    @Test (dependsOnMethods = "login")
    public void updateIssueType(){

        String issueType = "Bug";

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://soft.it-hillel.com.ua:8080/browse/QAAUT-1209");

        CreateIssue createIssue = new CreateIssue(driver);
        createIssue.changeTypeKeyboard( "type-val", "issuetype-field", issueType);

        driver.navigate().refresh();

        String issuetypefield = driver.findElement(By.xpath("//*[@id=\"type-val\"]")).getText();
        String typeval = driver.findElement(By.xpath("//*[@id=\"type-val\"]")).getText();

        System.out.println("issuetypefield " + issuetypefield);
        System.out.println("typeval " + typeval);

        assertEquals(issuetypefield, issueType);
        driver.close();

    }

    @Test (dependsOnMethods = "login")
    public void updateIssuePriority(){

        String eIssuePriority = "Low";

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://soft.it-hillel.com.ua:8080/browse/QAAUT-1209");

        CreateIssue createIssue = new CreateIssue(driver);
        createIssue.changeTypeKeyboard( "priority-val", "priority-field", eIssuePriority);

        driver.navigate().refresh();

        String aIssuePriority = driver.findElement(By.xpath("//*[@id=\"priority-val\"]")).getText();
        assertEquals(aIssuePriority, eIssuePriority);
        driver.close();
    }

    @Test (dependsOnMethods = "login")
    public void updateIComment(){

        String eComment = "new comment";

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://soft.it-hillel.com.ua:8080/browse/QAAUT-1209");

        CreateIssue createIssue = new CreateIssue(driver);

        createIssue.clickCreateIssue("footer-comment-button");
        createIssue.fieldField("comment", "new comment");
        createIssue.clickCreateIssue("issue-comment-add-submit");

        driver.navigate().refresh();

        String aComment = driver.findElement(By.xpath( "//*[@id=\"activitymodule\"]/div[2]/div[2]")).getText();
        assertTrue(aComment.contains(eComment));
        driver.close();
    }

    @Test (dependsOnMethods = "login")
    public void updateIssueTitle(){

        String eIssueTitle = "Selenium_test_2";

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://soft.it-hillel.com.ua:8080/browse/QAAUT-1209");

        CreateIssue createIssue = new CreateIssue(driver);

        createIssue.changeTypeKeyboard( "summary-val", "summary", eIssueTitle);

        driver.navigate().refresh();

        String aIssueTitle = driver.findElement(By.xpath("//*[@id=\"summary-val\"]")).getText();
        assertTrue(aIssueTitle.contains(eIssueTitle));
        driver.close();

    }






//    delete


}
