package tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.devtools.DevTools;
import org.testng.annotations.*;
import utils.DriverManager;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class ChromeDebugging extends DriverManager {

    @Parameters("browser")
    @BeforeClass (groups = {"hook"})
    void beforeClass(@Optional("browser") String browser) throws MalformedURLException {
        setDriver(browser);
    }

    @AfterClass (groups = {"hook"})
    void teardown() {
        driver.quit();
    }

    @Test(groups = {"version4"})
     void chromeDebuggingV4Test() throws InterruptedException {
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

}
