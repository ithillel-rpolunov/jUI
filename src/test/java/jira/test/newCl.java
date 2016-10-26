package jira.test;

/**
 * Created by Vasya on 26.10.2016.
 */



import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class newCl {
        private WebDriver driver;
        private String baseUrl;
        private boolean acceptNextAlert = true;
        private StringBuffer verificationErrors = new StringBuffer();

        @BeforeClass(alwaysRun = true)
        public void setUp() throws Exception {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            baseUrl = "http://soft.it-hillel.com.ua:8080/secure/Dashboard.jspa";
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }

        @Test
        public void testUntitled2() throws Exception {
            driver.get(baseUrl + "/secure/Dashboard.jspa");
            driver.findElement(By.id("create_link")).click();
            for (int second = 0;; second++) {
                if (second >= 60) fail("timeout");
                try { if (isElementPresent(By.id("create-issue-submit"))) break; } catch (Exception e) {}
                Thread.sleep(1000);
            }

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
            assertTrue(closeAlertAndGetItsText().matches("^[\\s\\S]*[\\s\\S]*[\\s\\S]*\n\nYou have entered new data on this page\\. If you navigate away from this page without first saving your data, the changes will be lost\\.\n\n[\\s\\S]*[\\s\\S]*[\\s\\S]*$"));
        }

        @AfterClass(alwaysRun = true)
        public void tearDown() throws Exception {
            driver.quit();
            String verificationErrorString = verificationErrors.toString();
            if (!"".equals(verificationErrorString)) {
                fail(verificationErrorString);
            }
        }

        private boolean isElementPresent(By by) {
            try {
                driver.findElement(by);
                return true;
            } catch (NoSuchElementException e) {
                return false;
            }
        }

        private boolean isAlertPresent() {
            try {
                driver.switchTo().alert();
                return true;
            } catch (NoAlertPresentException e) {
                return false;
            }
        }

        private String closeAlertAndGetItsText() {
            try {
                Alert alert = driver.switchTo().alert();
                String alertText = alert.getText();
                if (acceptNextAlert) {
                    alert.accept();
                } else {
                    alert.dismiss();
                }
                return alertText;
            } finally {
                acceptNextAlert = true;
            }
        }
    }


