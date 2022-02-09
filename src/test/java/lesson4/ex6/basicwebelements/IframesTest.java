package lesson4.ex6.basicwebelements;

import lesson4.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class IframesTest extends TestBase {
    @BeforeMethod
    public void getURL() {
        driver.get("https://seleniumui.moderntester.pl/iframes.php");
    }

    @Test
    public void shouldFillFormsInIFrames() {
        driver.switchTo().frame(0);
        driver.findElement(By.id("inputFirstName3")).sendKeys("Jan");
        driver.findElement(By.id("inputSurname3")).sendKeys("Kowalski");

        driver.switchTo().parentFrame().switchTo().frame(1);
        driver.findElement(By.id("inputLogin")).sendKeys("MyLogin123");
        driver.findElement(By.id("inputPassword")).sendKeys("MyPassword123");
        new Select(driver.findElement(By.id("inlineFormCustomSelectPref"))).selectByIndex(1);

        driver.switchTo().parentFrame();
        driver.findElement(By.cssSelector(".nav-ite > a")).click();

    }
}