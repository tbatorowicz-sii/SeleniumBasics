package lesson4.ex3;

import lesson4.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class URLTest extends TestBase {

    @Test
    public void testURLCheck() {
        driver.navigate().to("https://seleniumui.moderntester.pl/");
        Assert.assertEquals(driver.getCurrentUrl(), "https://seleniumui.moderntester.pl/");
    }
}