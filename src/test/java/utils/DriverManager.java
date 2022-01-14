package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverManager {
    public WebDriver driver;
    public WebDriverWait wait;
    public JavascriptExecutor js;
    public String baseURL;

    public void setDriver(String testBrowser) throws MalformedURLException {
//SET BROWSER DRIVER FILE PATH
        //No need to set for Grid(RemoteWebDriver) and Bonigarcia. They don't need these settings
        //System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        //System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver");
        /////////////////////////////
        switch (testBrowser) {
            case "grid-firefox": {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("start-maximized");
                //v3 Grid
                //driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptions);
                //v4 Grid
                driver = new RemoteWebDriver(new URL("http://localhost:4444"), firefoxOptions);
                System.out.println("***** Selenium Grid Firefox *****");
                break;
            }
            case "grid-chrome": {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);
                System.out.println("***** Selenium Grid Chrome *****");
                break;
            }
            case "firefox": {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("start-maximized");
                driver = new FirefoxDriver(firefoxOptions);
                System.out.println("***** Browser is firefox *****");
                break;
            }
            case "firefox-headless": {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("start-maximized");
                firefoxOptions.addArguments("--headless");
                driver = new FirefoxDriver(firefoxOptions);
                System.out.println("***** Browser is firefox *****");
                break;
            }
            case "chrome": {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
                driver = new ChromeDriver(chromeOptions);
                System.out.println("***** Browser is chrome *****");
                break;
            }
            case "chrome-headless": {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
                chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
                System.out.println("***** Browser is chrome-headless *****");
                break;
            }
            default: {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                System.out.println("***** Browser is chrome-bonigarcia *****");
                break;
            }
        }
        js = (JavascriptExecutor) driver;
        baseURL = "https://www.gloriajeans.com.tr/";
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 20);
    }


}
