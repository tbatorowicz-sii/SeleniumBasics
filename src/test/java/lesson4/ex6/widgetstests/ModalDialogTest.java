package lesson4.ex6.widgetstests;

import lesson4.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class ModalDialogTest extends TestBase {
    @BeforeMethod
    public void getURL() {
        driver.get("https://seleniumui.moderntester.pl/modal-dialog.php");
    }

    @Test
    public void shouldTestIfInputEqualsOutput() {
        WebElement createButton = driver.findElement(By.id("create-user"));
        createButton.click();
        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement emailInput = driver.findElement(By.id("email"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement confirmButton = driver.findElement(By.cssSelector("button.ui-button:nth-child(1)"));
        WebElement cancelButton = driver.findElement(By.cssSelector(".ui-dialog-buttonset > button:nth-child(2)"));
        cancelButton.click();

        IntStream.range(0, 2).forEach(i -> createNewUser(nameInput, emailInput, passwordInput, confirmButton, createButton,
                returnRandomName(), returnRandomEmail(), returnRandomPassword()));
    }

    public String returnRandomName() {
        int nameLength = new Random().nextInt(1, 15);
        int surnameLength = new Random().nextInt(1, 17 - (nameLength + 1));
        StringBuilder name = new StringBuilder();
        StringBuilder surname = new StringBuilder();
        char[] nameChars = "qwertyuiopasdfghjklzxcvbnm".toCharArray();
        while (name.length() != nameLength) {
            name.append(nameChars[(new Random().nextInt(nameChars.length))]);
        }
        while (surname.length() != surnameLength) {
            surname.append(nameChars[(new Random().nextInt(nameChars.length))]);
        }
        String sName = String.valueOf(name);
        String sSurname = String.valueOf(surname);
        sName = sName.substring(0, 1).toUpperCase() + sName.substring(1);
        sSurname = sSurname.substring(0, 1).toUpperCase() + sSurname.substring(1);
        return sName + " " + sSurname;
    }

    public String returnRandomEmail() {
        String[] result = returnRandomName().split(" ");
        return result[0].toLowerCase() + result[1].toLowerCase() + "@gmail.com";
    }

    public static String returnRandomPassword() {
        StringBuilder password = new StringBuilder();
        int passwordLength = new Random().nextInt(5, 17);
        char[] passwordChars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890".toCharArray();
        while (password.length() != passwordLength) {
            password.append(passwordChars[(new Random().nextInt(passwordChars.length))]);
        }
        return password.toString();
    }

    public void createNewUser(WebElement nInput, WebElement eInput, WebElement pInput,
                              WebElement confBtn, WebElement createBtn,
                              String name, String email, String password) {
        createBtn.click();
        nInput.clear();
        nInput.sendKeys(name);
        eInput.clear();
        eInput.sendKeys(email);
        pInput.clear();
        pInput.sendKeys(password);
        confBtn.click();
        assertion(name, email, password);
    }

    public void assertion(String name, String email, String password) {
        List<WebElement> rows = driver.findElements(By.cssSelector("tbody tr"));
        String newRowName = rows.get(rows.size() - 1).findElements(By.cssSelector("td")).get(0).getText();
        String newRowEmail = rows.get(rows.size() - 1).findElements(By.cssSelector("td")).get(1).getText();
        String newRowPassword = rows.get(rows.size() - 1).findElements(By.cssSelector("td")).get(2).getText();
        Assert.assertEquals(newRowName, name);
        Assert.assertEquals(newRowEmail, email);
        Assert.assertEquals(newRowPassword, password);
    }
}
