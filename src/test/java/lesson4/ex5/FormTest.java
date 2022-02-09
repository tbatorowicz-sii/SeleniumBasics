package lesson4.ex5;

import lesson4.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class FormTest extends TestBase {

    @Test
    public void shouldFillFormWithSuccess(){
        driver.get("https://seleniumui.moderntester.pl/form.php");

        int fileCountBeforeDownload=new File("C:\\testSeleniumDownloadMyDirectory").list().length;

        driver.findElement(By.className("btn-secondary")).click();


        driver.findElement(By.id("inputFirstName3")).sendKeys("Jan");
        driver.findElement(By.id("inputLastName3")).sendKeys("Kowalski");
        driver.findElement(By.id("inputEmail3")).sendKeys("kowalski.jan@gmail.com");

        driver.findElements(By.name("gridRadiosSex"))
                .get(new Random().nextInt(driver.findElements(By.name("gridRadiosSex")).size()))
                .click();

        driver.findElement(By.id("inputAge3")).sendKeys("50");

        driver.findElements(By.name("gridRadiosExperience"))
                .get(new Random().nextInt(driver.findElements(By.name("gridRadiosExperience")).size()))
                .click();

        driver.findElement(By.id("gridCheckAutomationTester")).click();

        Select selectContinent = new Select(driver.findElement(By.id("selectContinents")));
        selectContinent.selectByIndex(new Random().nextInt(1,7));

        Select selectCommand = new Select(driver.findElement(By.id("selectSeleniumCommands")));
        selectCommand.selectByIndex(2);
        selectCommand.selectByIndex(3);

        File file = new File("src/main/resources/text.txt");

        driver.findElement(By.id("chooseFile")).sendKeys(file.getAbsolutePath());

        driver.findElement(By.className("btn-primary")).click();

        String getText = driver.findElement(By.id("validator-message")).getText();

        int fileCountAfterDownload=new File("C:\\testSeleniumDownloadMyDirectory").list().length;

        File f = new File("C:\\testSeleniumDownloadMyDirectory");
        ArrayList<String> names = new ArrayList<String>(Arrays.asList(f.list()));
        Boolean containsCertainFile = false;
        for (String name : names) {
            if (name.equals("test-file-to-download.xlsx")) {
                containsCertainFile = true;
                break;
            }
        }

        Assert.assertEquals(containsCertainFile, true);
        Assert.assertEquals(fileCountBeforeDownload+1, fileCountAfterDownload);
        Assert.assertEquals(getText, "Form send with success");

    }
}
