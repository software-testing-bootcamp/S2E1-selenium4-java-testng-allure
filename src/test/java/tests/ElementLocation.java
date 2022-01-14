package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import utils.DriverManager;

import java.net.MalformedURLException;

public class ElementLocation extends DriverManager {

    @Parameters("browser")
    @BeforeClass(groups = {"hook"})
    void beforeClass(@Optional("browser") String browser) throws MalformedURLException {
        setDriver(browser);
    }

    @AfterClass(groups = {"hook"})
    void teardown() {
        driver.quit();
    }

    @Test(groups = {"version4"})
    public void elementLocationTest() throws InterruptedException {
        driver.get(baseURL);
        Thread.sleep(3000);
        driver.findElements(By.tagName("button")).get(0).click();
        Thread.sleep(2000);
        driver.findElement(By.linkText("Menü")).click();
        Thread.sleep(2000);
        WebElement targetElement = driver.findElement(By.linkText("Soğuk İçeceklerimiz"));
        System.out.println("Height: " + targetElement.getRect().getDimension().getHeight());
        System.out.println("Width: " + targetElement.getRect().getDimension().getWidth());
        System.out.println("X Location: " + targetElement.getRect().getX());
        System.out.println("Y Location: " + targetElement.getRect().getY());
    }
}
