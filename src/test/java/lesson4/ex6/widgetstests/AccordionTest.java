package lesson4.ex6.widgetstests;

import lesson4.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AccordionTest extends TestBase {
    @BeforeMethod
    public void getURL() {
        driver.get("https://seleniumui.moderntester.pl/accordion.php");
    }

    @Test
    public void shouldPrintOutTextFromEachSection() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        System.out.println(driver.findElement(By.id("ui-id-2")).getText());

        driver.findElement(By.id("ui-id-3")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ui-id-4"))));
        System.out.println(driver.findElement(By.id("ui-id-4")).getText());

        driver.findElement(By.id("ui-id-5")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#ui-id-6 > ul:nth-child(2) > li:nth-child(3)"))));
        System.out.println(driver.findElement(By.id("ui-id-6")).getText());

        driver.findElement(By.id("ui-id-7")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#ui-id-8 > p:nth-child(2)"))));
        System.out.println(driver.findElement(By.id("ui-id-8")).getText());

    }

}
