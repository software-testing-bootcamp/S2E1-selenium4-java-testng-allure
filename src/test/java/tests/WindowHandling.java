package tests;

import org.openqa.selenium.WindowType;
import org.testng.annotations.*;
import utils.DriverManager;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class WindowHandling extends DriverManager {

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

    @Test(groups = {"version4"})
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
}

