package lesson4.ex6.interactions;

import lesson4.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DraggableTest extends TestBase {
    @BeforeMethod
    public void getURL() {
        driver.get("https://seleniumui.moderntester.pl/draggable.php");
    }

    @Test
    public void shouldDrag() {
        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement pageBody = driver.findElement(By.cssSelector("html"));

        int draggableXSize = draggable.getSize().width;
        int draggableYSize = draggable.getSize().height;
        int htmlXSize = pageBody.getSize().width;
        int htmlYSize = pageBody.getSize().height;

        Actions action = new Actions(driver);
        action.dragAndDropBy(draggable, htmlXSize - draggableXSize * 2, -draggableYSize).perform();
        action.dragAndDropBy(draggable, 0, htmlYSize).perform();
        action.dragAndDropBy(draggable, -htmlXSize / 2, -htmlYSize / 2).perform();
        action.dragAndDropBy(draggable, -htmlXSize / 2 + draggableXSize, htmlYSize / 2).perform();

        Assert.assertEquals(draggable.getText(), "Drag me around");
    }
}
