package lesson4.ex6.basicwebelements;

import lesson4.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AlertsTest extends TestBase {

    @BeforeMethod
    public void getURL(){
        driver.get("https://seleniumui.moderntester.pl/alerts.php");
    }

    @Test
    public void shouldPassSimpleAlert(){
        driver.findElement(By.id("simple-alert")).click();
        driver.switchTo().alert().accept();
        Assert.assertEquals(driver.findElement(By.id("simple-alert-label")).getText(), "OK button pressed");
    }

    @Test
    public void shouldFillPromptAlert(){
        driver.findElement(By.id("prompt-alert")).click();
        driver.switchTo().alert().sendKeys("Lord Vader");
        driver.switchTo().alert().accept();
        Assert.assertEquals(driver.findElement(By.id("prompt-label")).getText(), "Hello Lord Vader! How are you today?");
    }

    @Test
    public void shouldAcceptAndDismissAlert(){
        driver.findElement(By.id("confirm-alert")).click();
        driver.switchTo().alert().accept();
        Assert.assertEquals(driver.findElement(By.id("confirm-label")).getText(), "You pressed OK!");
        driver.findElement(By.id("confirm-alert")).click();
        driver.switchTo().alert().dismiss();
        Assert.assertEquals(driver.findElement(By.id("confirm-label")).getText(), "You pressed Cancel!");
    }

    @Test
    public void shouldWaitForDelayedAlert(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.findElement(By.id("delayed-alert")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        Assert.assertEquals(driver.findElement(By.id("delayed-alert-label")).getText(), "OK button pressed");
    }
}
