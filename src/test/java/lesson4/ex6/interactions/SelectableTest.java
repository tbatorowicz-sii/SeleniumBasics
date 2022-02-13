package lesson4.ex6.interactions;

import lesson4.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SelectableTest extends TestBase {
    @BeforeMethod
    public void getURL() {
        driver.get("https://seleniumui.moderntester.pl/selectable.php");
    }

    @Test
    public void shouldSelect() {
        new Actions(driver)
                .keyDown(Keys.CONTROL)
                .click(driver.findElement(By.xpath("//li[.='Item 1']")))
                .click(driver.findElement(By.xpath("//li[.='Item 3']")))
                .click(driver.findElement(By.xpath("//li[.='Item 4']")))
                .keyUp(Keys.CONTROL)
                .perform();
        Assert.assertEquals(driver.findElement(By.id("feedback")).getText(), "You've selected: #1 #3 #4.");
    }
}
