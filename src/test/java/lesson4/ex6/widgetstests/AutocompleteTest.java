package lesson4.ex6.widgetstests;

import lesson4.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class AutocompleteTest extends TestBase {
    @BeforeMethod
    public void getURL(){
        driver.get("https://seleniumui.moderntester.pl/autocomplete.php");
    }

    @Test
    public void shouldDisplaySameInputAsSelectedOption(){
        WebElement search = driver.findElement(By.id("search"));
        search.sendKeys("a");

        List<WebElement> results = driver.findElements(By.className("ui-menu-item"));
        results.forEach(e -> System.out.println(e.getText()));

        WebElement element = results.get(new Random().nextInt(results.size()));
        element.click();
        
        Assert.assertEquals(search.getText(),element.getText());
    }
}
