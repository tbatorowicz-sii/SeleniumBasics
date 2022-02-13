package lesson4.ex6.basicwebelements;

import lesson4.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WindowsTabsTest extends TestBase {
    @BeforeMethod
    public void getURL() {
        driver.get("https://seleniumui.moderntester.pl/windows-tabs.php");
    }

    @Test
    public void shouldDoWindowsTabsExercise() {
        driver.findElement(By.id("newBrowserWindow")).click();
        switchToLastOpenedWindow(driver);
        tableTestExercise(driver);
        closeAndSwitchToMainWindow(driver);
        driver.findElement(By.id("newMessageWindow")).click();
        switchToLastOpenedWindow(driver);
        System.out.println(driver.findElement(By.cssSelector("body")).getText());
        closeAndSwitchToMainWindow(driver);
        driver.findElement(By.id("newBrowserTab")).click();
        switchToLastOpenedWindow(driver);
        tableTestExercise(driver);
        driver.close();
    }

    public void closeAndSwitchToMainWindow(WebDriver driver) {
        driver.close();
        driver.switchTo().window(driver.getWindowHandles().stream().toList().get(0));
    }

    public void switchToLastOpenedWindow(WebDriver driver) {
        driver.switchTo().window(
                driver.getWindowHandles().stream().toList().get(
                        driver.getWindowHandles().stream().toList().size() - 1));
    }

    public void tableTestExercise(WebDriver driver) {
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
