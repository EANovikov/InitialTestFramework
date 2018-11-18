package utilities.driver;

import config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;

public class WebDriverFactory {

    private static String browser = System.getProperty("browser");
    private final String driverPath = "src/test/resources/driver/";

    private WebDriverFactory() {
    }

    private static WebDriverFactory instance = new WebDriverFactory();

    public static WebDriverFactory getInstance() {
        return instance;
    }

    ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() {
        @Override
        protected WebDriver initialValue() {
            File file;
            if (browser == null) {
                browser = ConfigReader.getBrowser();
            }
            if (browser.equalsIgnoreCase(Browser.CHROME.getBrowserName())) {
                file = new File(driverPath + "chromedriver.exe");
                System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
                return new ChromeDriver();
            } else if (browser.equalsIgnoreCase(Browser.IE.getBrowserName())) {
                file = new File(driverPath + "IEDriverServer.exe");
                System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
                return new InternetExplorerDriver();
            }
            return null;
        }
    };

    public WebDriver getDriver() {
        return driver.get();
    }

    public WebDriver getDriver(String url) {
        driver.get().get(url);
        driver.get().manage().window().maximize();
        return driver.get();
    }

    public void closeDriver() {
        driver.get().quit();
        driver.remove();
    }

}
