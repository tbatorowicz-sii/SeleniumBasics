package lesson4.ex6.widgetstests;

import lesson4.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TooltipTest extends TestBase {
    @BeforeMethod
    public void getURL() {
        driver.get("https://seleniumui.moderntester.pl/tooltip.php");
    }

    @Test
    public void shouldPrintOutTextFromTooltip() {
        System.out.println(driver.findElement(By.cssSelector(".content > p:nth-child(1) > a")).getAttribute("title"));
    }
}