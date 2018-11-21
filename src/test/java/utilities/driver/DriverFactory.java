package utilities.driver;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import utilities.config.ConfigReader;

import java.io.File;

public class DriverFactory {


    private static String browser = System.getProperty("browser");
    private final static String driverPath = "src/test/resources/driver/";
    private static Logger logger= Logger.getLogger("Driver Factory");
    private static WebDriver driver ;

    public static WebDriver initDriver() {
        if (browser == null) {
            browser = ConfigReader.getBrowser();
        }
        File file;
        if (browser.equalsIgnoreCase(Browser.CHROME.getBrowserName())) {
            file = new File(driverPath + "chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            logger.debug("Starting Chrome driver");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase(Browser.IE.getBrowserName())) {
            file = new File(driverPath + "IEDriverServer.exe");
            System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
            logger.debug("Starting Internet Explorer driver");
            driver = new InternetExplorerDriver();
        }
        return driver;
    }

    public static WebDriver initDriver(String url) {
        if (browser == null) {
            browser = ConfigReader.getBrowser();
        }
        File file;
        if (browser.equalsIgnoreCase(Browser.CHROME.getBrowserName())) {
            file = new File(driverPath + "chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            logger.debug("Starting Chrome driver");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase(Browser.IE.getBrowserName())) {
            file = new File(driverPath + "IEDriverServer.exe");
            System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
            logger.debug("Starting Internet Explorer driver");
            driver = new InternetExplorerDriver();
        }
        return getDriver(url);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriver getDriver(String url) {
        logger.info("Navigating to URL " + url);
        driver.navigate().to(url);
        if (driver.findElement(By.tagName("body")).getText().contains("ERR_CONNECTION_CLOSED")) {
            driver.navigate().refresh();
        }
        logger.debug("Maximizing the window");
        driver.manage().window().maximize();
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            logger.debug("Closing driver");
            driver.manage().deleteAllCookies();
            driver.quit();
        } else {
            logger.debug("WebDriver has instance is null (already closed)");
        }


    }

}
