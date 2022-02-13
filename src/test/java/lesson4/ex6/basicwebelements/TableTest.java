package lesson4.ex6.basicwebelements;

import lesson4.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TableTest extends TestBase {

    @BeforeMethod
    public void getURL() {
        driver.get("https://seleniumui.moderntester.pl/table.php");
    }

    @Test
    public void shouldPrintMountainsHigherThan4000m() {
        driver.findElements(By.cssSelector("tbody tr")).forEach(row -> {
            int height = Integer.parseInt(row.findElements(By.cssSelector("td")).get(3).getText());
            if (height < 4000) return;
            String rank = row.findElement(By.cssSelector("th")).getText();
            String peak = row.findElements(By.cssSelector("td")).get(0).getText();
            String m = row.findElements(By.cssSelector("td")).get(1).getText();
            System.out.println(rank + ". " + peak + " " + m);
        });
    }
}