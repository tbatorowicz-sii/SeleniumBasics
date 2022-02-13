package lesson4.ex6.widgetstests;

import lesson4.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class MenuTest extends TestBase {
    @BeforeMethod
    public void getURL() {
        driver.get("https://seleniumui.moderntester.pl/menu-item.php");
    }

    @Test
    public void shouldPickModern() {
        Actions action = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));

        action.moveToElement(driver.findElement(By.id("ui-id-9"))).perform();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ui-id-13"))));

        action.moveToElement(driver.findElement(By.id("ui-id-13"))).perform();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ui-id-16"))));

        action.moveToElement(driver.findElement(By.id("ui-id-16"))).perform();
        Assert.assertEquals(driver.findElement(By.id("ui-id-16")).getAttribute("class"), "ui-menu-item-wrapper ui-state-active");
    }
}