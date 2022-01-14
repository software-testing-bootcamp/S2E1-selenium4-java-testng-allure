package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import utils.DriverManager;

import java.io.File;
import java.net.MalformedURLException;

public class Screenshot extends DriverManager {

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
    public void screenshotV3Test() throws Exception {
        driver.get(baseURL);
        Thread.sleep(2000);
        takeSnapShotV3(driver, "src/test/resources/screenshots/testv3-chrome.png");
    }

    @Test(groups = {"version4"})
    public void screenshotV4FirefoxTest() throws Exception {
        driver.close();
        FirefoxDriver firefoxDriver = new FirefoxDriver();
        firefoxDriver.get(baseURL);
        Thread.sleep(2000);
        File SrcFile = ((FirefoxDriver) firefoxDriver).getFullPageScreenshotAs(OutputType.FILE);
        File DestFile = new File("src/test/resources/screenshots/testv4-firefox.png");
        FileUtils.copyFile(SrcFile, DestFile);
        firefoxDriver.quit();
    }

    @Test(groups = {"version4"})
    public void screenshotV4ElementTest() throws Exception {
        driver.get(baseURL);
        Thread.sleep(3000);
        driver.findElements(By.tagName("button")).get(0).click();
        Thread.sleep(2000);
        driver.findElement(By.linkText("Menü")).click();
        Thread.sleep(2000);
        WebElement targetElement = driver.findElement(By.linkText("Soğuk İçeceklerimiz"));
        File SrcFile = targetElement.getScreenshotAs(OutputType.FILE);
        File DestFile = new File("src/test/resources/screenshots/testv4-element.png");
        FileUtils.copyFile(SrcFile, DestFile);
    }

    private static void takeSnapShotV3(WebDriver webdriver, String fileWithPath) throws Exception {
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath);
        FileUtils.copyFile(SrcFile, DestFile);
    }

}
