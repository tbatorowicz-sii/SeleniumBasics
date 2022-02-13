package lesson4.ex6.widgetstests;

import lesson4.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ProgressbarTest extends TestBase {
    @BeforeMethod
    public void getURL() {
        driver.get("https://seleniumui.moderntester.pl/progressbar.php");
    }

    @Test
    public void shouldDisplayComplete() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.textToBe(By.className("progress-label"), "Complete!"));
        Assert.assertEquals(driver.findElement(By.className("progress-label")).getText(), "Complete!");
    }
}