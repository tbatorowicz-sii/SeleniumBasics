package lesson4.ex6.interactions;

import lesson4.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class SortableTest extends TestBase {
    @BeforeMethod
    public void getURL() {
        driver.get("https://seleniumui.moderntester.pl/sortable.php");
    }

    @Test
    public void shouldSortElements() {
        int bound = 7;
        ArrayList<Integer> numbers = getShuffledNumbers(bound);
        ArrayList<WebElement> items = getItemElements(bound);
        IntStream.range(0, bound).forEach(i -> {
            new Actions(driver)
                    .dragAndDrop(items.get(numbers.get(i) - 1),
                            driver.findElement(By.xpath("//ul[@id='sortable']/li[" + (i + 1) + "]")))
                    .perform();
        });
        System.out.println(numbers);
    }

    public ArrayList<Integer> getShuffledNumbers(int bound) {
        ArrayList<Integer> numbers = new ArrayList<>();
        IntStream.range(0, bound).forEach(i -> numbers.add(i + 1));
        ArrayList<Integer> table = new ArrayList<>();
        int numbersSize = numbers.size();
        while (table.size() != numbersSize) {
            int random = new Random().nextInt(0, numbers.size());
            table.add(numbers.get(random));
            numbers.remove(random);
        }
        return table;
    }

    public ArrayList<WebElement> getItemElements(int bound) {
        ArrayList<WebElement> items = new ArrayList<>();
        IntStream.range(0, bound).forEach(i -> items
                .add(driver.findElement(By.xpath("//li[.='Item " + (i + 1) + "']"))));
        return items;
    }
}