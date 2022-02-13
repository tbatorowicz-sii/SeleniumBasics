package lesson4.ex6.widgetstests;

import lesson4.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class DatepickerTest extends TestBase {
    @BeforeMethod
    public void getURL() {
        driver.get("https://seleniumui.moderntester.pl/datepicker.php");
    }

    @Test
    public void shouldPickDates() {
        driver.findElement(By.id("datepicker")).click();
        int todayDay = getTodayDay();
        int todayMonth = getAttribute("data-month");
        int todayYear = getAttribute("data-year");

        //Months: [Jan-Dec] = [0-11]
        //Days: 0 = random day

        //1. Today
        clickTheDate(todayYear, todayMonth, todayDay);

        //2. 1st day from next month
        clickTheDate(todayYear, todayMonth + 1, 1);

        //3. Last day from January in next year
        clickTheDate(todayYear + 1, 0, 31);

        //4. Select same day again (same was selected in step 3)
        clickTheDate(todayYear + 1, 0, 31);

        //5. Random day from previous month
        clickTheDate(todayYear, todayMonth - 1, 0);

        //6. Random date from last year
        clickTheDate(todayYear - 1, getRandomMonth(), 0);
    }

    public int getTodayDay() {
        return Integer.parseInt(driver.findElement(By.className("ui-state-highlight")).getText());
    }

    public int getAttribute(String attribute) {
        List<WebElement> daysList = driver.findElements(By.cssSelector(".ui-datepicker-calendar > tbody > tr > td"));
        return Integer.parseInt(daysList.get(15).getAttribute(attribute));
    }

    public int getRandomDayOfMonth() {
        List<WebElement> daysList = driver.findElements(By.cssSelector(".ui-datepicker-calendar > tbody > tr > td"));
        while (daysList.get(0).getText().length() == 2) daysList.remove(0);
        while (daysList.get(daysList.size() - 1).getText().length() == 1) daysList.remove(daysList.size() - 1);
        return Integer.parseInt(daysList.get(new Random().nextInt(daysList.size() - 1)).getText());
    }

    public int getRandomMonth() {
        return new Random().nextInt(11);
    }

    public void clickTheDate(int year, int month, int day) {
        driver.findElement(By.id("datepicker")).click();
        findYearMonth(getAttribute("data-year"), getAttribute("data-month"), year, month);
        if (day == 0) day = getRandomDayOfMonth();
        dayOfMonthClick(String.valueOf(day));
        System.out.println(returnDateString(year, month, day));
        Assert.assertEquals(driver.findElement(By.id("datepicker")).getAttribute("value"), returnDateString(year, month, day));
    }

    public void dayOfMonthClick(String day) {
        List<WebElement> daysList = driver.findElements(By.cssSelector(".ui-datepicker-calendar > tbody > tr > td"));
        while (daysList.get(0).getText().length() == 2) daysList.remove(0);
        while (!daysList.get(0).getText().equals(day)) daysList.remove(0);
        daysList.get(0).click();
    }

    public void findYearMonth(int actualY, int actualM, int searchenY, int searchenM) {

        By next = By.cssSelector(".ui-datepicker-next");
        By prev = By.cssSelector(".ui-datepicker-prev");

        if (searchenM > 11) {
            searchenY += searchenM / 12;
            searchenM = searchenM % 12;
        } else if (searchenM < 0) {
            searchenY += (searchenM / 12) - 1;
            searchenM = (searchenM % 12) + 12;
        }

        while (actualY < searchenY) {
            waitToBeClickableAndClick(next);
            if (actualM != 11) actualM++;
            else {
                actualM = 0;
                actualY++;
            }
        }

        while (actualY > searchenY) {
            waitToBeClickableAndClick(prev);
            if (actualM != 0) actualM--;
            else {
                actualM = 11;
                actualY--;
            }
        }

        while (actualM < searchenM) {
            waitToBeClickableAndClick(next);
            actualM++;
        }

        while (actualM > searchenM) {
            waitToBeClickableAndClick(prev);
            actualM--;
        }
    }

    public void waitToBeClickableAndClick(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }

    public String returnDateString(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return new SimpleDateFormat("MM/dd/yyyy").format(calendar.getTime());
    }
}