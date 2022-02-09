package lesson4.ex6.basicwebelements;

import lesson4.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WindowsTabsTest extends TestBase {
    @BeforeMethod
    public void getURL(){
        driver.get("https://seleniumui.moderntester.pl/windows-tabs.php");
    }

    @Test
    public void shouldDoWindowsTabsExercise(){ //to do
        /*
        1. Click on 'New Browser Window' button
        2. Switch to a new window
        3. Execute test from 4th exercise on the newly opened window
        4. Close window
        5. Click 'New Message Window' button
        6. Switch to a new window
        7. Print out the text that is written in a new window
        8. Close window
        9. Click on 'New Browser Tab' button
        10. Switch to a new tab
        11. Execute test from 4th exercise on the new opened window
        12. Close tab
        */
        Boolean done = false;
        Assert.assertEquals(done, true);
    }
}
