package lesson4.ex6.widgetstests;

import lesson4.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

public class SliderTest extends TestBase {

    @BeforeMethod
    public void getURL() {
        driver.get("https://seleniumui.moderntester.pl/slider.php");
    }

    @Test
    public void shouldMoveSlider() {
        WebElement sliderButton = driver.findElement(By.id("custom-handle"));

        int[] values = {50, 80, 80, 20, 0};

        Arrays.stream(values).forEach(value -> {
            while ((Integer.parseInt(sliderButton.getText()) > value))
                sliderButton.sendKeys(Keys.ARROW_LEFT);
            while ((Integer.parseInt(sliderButton.getText()) < value))
                sliderButton.sendKeys(Keys.ARROW_RIGHT);
            Assert.assertEquals(Integer.parseInt(sliderButton.getText()), value);
        });
    }
}