package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import utils.DriverManager;

import java.net.MalformedURLException;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class RelativeLocators extends DriverManager {

    @Parameters("browser")
    @BeforeClass(groups = {"hook"})
    void beforeClass(@Optional("browser") String browser) throws MalformedURLException {
        setDriver(browser);
    }

    @AfterClass(groups = {"hook"})
    void teardown() {
        driver.quit();
    }

    @Test
    public void relativeLocatorsV3Test() throws InterruptedException {
        driver.get(baseURL);
        Thread.sleep(3000);
        driver.findElements(By.tagName("button")).get(0).click();
        Thread.sleep(2000);
        driver.findElement(By.linkText("Menü")).click();
        Thread.sleep(2000);
        driver.findElement(By.linkText("Soğuk İçeceklerimiz")).click();
        Thread.sleep(2000);
        WebElement firstDrink = driver.findElement(By.xpath("//*[contains(text(),'Very Vanilla Chiller')]"));
        WebElement secondDrink = driver.findElement(By.xpath("//*[contains(text(),'Créme Brulée Chiller')]"));
        WebElement thirdDrink = driver.findElement(By.xpath("//*[contains(text(),'GJ`s Original Iced Chocolate')]"));
        WebElement fourthDrink = driver.findElement(By.xpath("//*[contains(text(),'Italian Soda')]"));
        WebElement fifthDrink = driver.findElement(By.xpath("//*[contains(text(),'GJ`s Iced Coffee')]"));
        highlightElement(firstDrink);
        Thread.sleep(1000);
        highlightElement(secondDrink);
        Thread.sleep(1000);
        highlightElement(thirdDrink);
        Thread.sleep(1000);
        highlightElement(fourthDrink);
        Thread.sleep(1000);
        highlightElement(fifthDrink);
        Thread.sleep(1000);
    }

    @Test(groups = {"version4"})
    public void relativeLocatorsV4Test() throws InterruptedException {
        driver.get(baseURL);
        Thread.sleep(3000);
        driver.findElements(By.tagName("button")).get(0).click();
        Thread.sleep(2000);
        driver.findElement(By.linkText("Menü")).click();
        Thread.sleep(2000);
        driver.findElement(By.linkText("Soğuk İçeceklerimiz")).click();
        Thread.sleep(2000);

        WebElement firstDrink = driver.findElement(By.xpath("//*[contains(text(),'Very Vanilla Chiller')]"));
        WebElement secondDrink = driver.findElement(with(By.tagName("div")).toRightOf(firstDrink));
        WebElement thirdDrink = driver.findElement(with(By.tagName("div")).toRightOf(secondDrink));
        WebElement fourthDrink = driver.findElement(with(By.tagName("div")).toRightOf(thirdDrink));
        WebElement fifthDrink = driver.findElement(with(By.tagName("div")).below(firstDrink));
        highlightElement(firstDrink);
        Thread.sleep(1000);
        highlightElement(secondDrink);
        Thread.sleep(1000);
        highlightElement(thirdDrink);
        Thread.sleep(1000);
        highlightElement(fourthDrink);
        Thread.sleep(1000);
        highlightElement(fifthDrink);
        Thread.sleep(1000);
    }

    private WebElement highlightElement(WebElement element) {
        js.executeScript("arguments[0].style.border='3px solid orange'", element);
        return element;
    }
}
