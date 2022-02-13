package lesson4.ex6.widgetstests;

import lesson4.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class SelectableTest extends TestBase {
    @BeforeMethod
    public void getURL() {
        driver.get("https://seleniumui.moderntester.pl/selectmenu.php");
    }

    @Test
    public void shouldSelectAll() {
        driver.findElement(By.id("speed-button")).click();
        int randomSpeedIndex = new Random().nextInt(1, 6);
        driver.findElement(By.xpath("//ul[@id='speed-menu']/li[" + randomSpeedIndex + "]")).click();

        driver.findElement(By.id("files-button")).click();
        driver.findElement(By.xpath("//*[@id='files-menu']//*[text()='Some unknown file']")).click();

        driver.findElement(By.id("number-button")).click();
        driver.findElement(By.xpath("//ul[@id='number-menu']/li[5]")).click();

        driver.findElement(By.id("salutation-button")).click();
        int randomTitleIndex = new Random().nextInt(1, 6);
        driver.findElement(By.xpath("//ul[@id='salutation-menu']/li[" + randomTitleIndex + "]")).click();

    }
}