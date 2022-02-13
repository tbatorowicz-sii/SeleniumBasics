package lesson4.ex6.othertests;

import lesson4.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;


public class HighSiteTest extends TestBase {
    @BeforeMethod
    public void getURL() {
        driver.get("https://seleniumui.moderntester.pl/high-site.php");
    }

    @Test
    public void shouldScrollTillBtnVisible() throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        TakesScreenshot scrShot = (TakesScreenshot) driver;

        while (driver.findElements(By.id("scroll-button")).size() == 0)
            js.executeScript("window.scrollBy(0,45)");

        Assert.assertTrue(driver.findElement(By.id("scroll-button")).isDisplayed());

        FileUtils.copyFile(scrShot.getScreenshotAs(OutputType.FILE), new File("src\\main\\resources\\HighSiteTest.png"));
    }
}