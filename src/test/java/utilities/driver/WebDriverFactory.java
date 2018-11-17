package utilities.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class WebDriverFactory {

    private WebDriverFactory() {
    }

    private static WebDriverFactory instance = new WebDriverFactory();

    public static WebDriverFactory getInstance() {
        return instance;
    }

    ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() // thread local driver object for webdriver
    {
        @Override
        protected WebDriver initialValue() {
            File file = new File("src/main/resources/chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            return new ChromeDriver();
        }
    };

    public WebDriver getDriver()
    {
        return driver.get();
    }

    public void closeDriver()
    {
        driver.get().quit();
        driver.remove();
    }

}
