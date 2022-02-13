package lesson4.ex6.interactions;

import lesson4.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ResizableTest extends TestBase {
    @BeforeMethod
    public void getURL() {
        driver.get("https://seleniumui.moderntester.pl/resizable.php");
    }

    @Test
    public void shouldResizeWindow() {
        WebElement resizer = driver.findElement(By.className("ui-resizable-se"));
        Actions action = new Actions(driver);
        WebElement resizable = driver.findElement(By.id("resizable"));
        int resizableWidthBefore = resizable.getSize().width;
        int resizableHeightBefore = resizable.getSize().height;


        action.dragAndDropBy(resizer, 10 + 18, 0).perform();

        Assert.assertEquals(resizable.getSize().width, resizableWidthBefore + 10);
        resizableWidthBefore = resizable.getSize().width;


        action.dragAndDropBy(resizer, 0, 10 + 18).perform();

        Assert.assertEquals(resizable.getSize().height, resizableHeightBefore + 10);
        resizableHeightBefore = resizable.getSize().height;


        action.dragAndDropBy(resizer, 10 + 18, 10 + 18).perform();

        Assert.assertEquals(resizable.getSize().width, resizableWidthBefore + 10);
        Assert.assertEquals(resizable.getSize().height, resizableHeightBefore + 10);
    }

}
