package lesson4.ex6.basicwebelements;

import lesson4.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class TableTest extends TestBase{

    @BeforeMethod
    public void getURL(){
        driver.get("https://seleniumui.moderntester.pl/table.php");
    }

    @Test
    public void shouldPrintMountainsHigherThan4000m() {
        List<WebElement> rows = driver.findElements(By.cssSelector("tbody tr"));

        rows.forEach(row -> {
            int height = Integer.parseInt(row.findElements(By.cssSelector("td")).get(3).getText());
            if (height < 4000) return;
            String rank = row.findElement(By.cssSelector("th")).getText();
            String peak = row.findElements(By.cssSelector("td")).get(0).getText();
            String m = row.findElements(By.cssSelector("td")).get(1).getText();
            System.out.println(rank + ". " + peak + " " + m);
        });
    }
}
