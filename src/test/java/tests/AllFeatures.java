package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class AllFeatures {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private String baseURL;

    @BeforeMethod
    public void setup() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 20);
        js = (JavascriptExecutor) driver;
        baseURL = "https://www.gloriajeans.com.tr/";
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

    @Test
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

    @Test
    public void windowHandlingV3Test() throws InterruptedException {
        String openingUrl = "https://www.google.com.tr/";
        driver.get(openingUrl);
        Thread.sleep(2000);

        js.executeScript("window.open('" + openingUrl + "', 'new_window')");
        Thread.sleep(2000);

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); //switches to new tab
        driver.get(baseURL);
        Thread.sleep(2000);

        driver.switchTo().window(tabs.get(0)); // switch back to main screen
        driver.get("https://testingbootcamp.com/");
        Thread.sleep(2000);
    }

    @Test
    public void windowHandlingV4Test() throws InterruptedException {
        String openingUrl = "https://www.google.com.tr/";
        driver.get(openingUrl);
        String firstWindow = driver.getWindowHandle();
        Thread.sleep(2000);

        driver.switchTo().newWindow(WindowType.TAB);
        Thread.sleep(2000);
        driver.get(baseURL);
        Thread.sleep(2000);

        driver.switchTo().window(firstWindow);
        driver.get("https://testingbootcamp.com/");
        Thread.sleep(2000);
    }

    @Test
    public void screenshotV3Test() throws Exception {
        driver.get(baseURL);
        Thread.sleep(2000);
        takeSnapShotV3(driver, "src/test/resources/screenshots/testv3-chrome.png");
    }

    @Test
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

    @Test
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

    @Test
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

    @Test
    public void timeoutGetters() {
        System.out.println(driver.manage().timeouts().getImplicitWaitTimeout());
    }

    @Test
    public void chromeDebuggingV4Test() throws InterruptedException {
        driver.close();
        ChromiumDriver mobileWebDriver = new ChromeDriver();
        DevTools devTools = mobileWebDriver.getDevTools();
        devTools.createSession();
        Map mWebDeviceMetrics = new HashMap() {{
            put("width", 600);
            put("height", 1000);
            put("mobile", true);
            put("deviceScaleFactor", 50);
        }};
        mobileWebDriver.executeCdpCommand("Emulation.setDeviceMetricsOverride", mWebDeviceMetrics);
        mobileWebDriver.get(baseURL);
        Thread.sleep(5000);
        Map desktopWebDeviceMetrics = new HashMap() {{
            put("width", 1280);
            put("height", 1000);
            put("mobile", false);
            put("deviceScaleFactor", 1280);
        }};
        mobileWebDriver.executeCdpCommand("Emulation.setDeviceMetricsOverride", desktopWebDeviceMetrics);
        mobileWebDriver.get(baseURL);
        Thread.sleep(5000);
        mobileWebDriver.quit();
    }


    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    private WebElement highlightElement(WebElement element) {
        js.executeScript("arguments[0].style.border='3px solid orange'", element);
        return element;
    }

    private static void takeSnapShotV3(WebDriver webdriver, String fileWithPath) throws Exception {
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath);
        FileUtils.copyFile(SrcFile, DestFile);

    }


}
