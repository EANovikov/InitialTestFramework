package utilities.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class WebDriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        try {
            if (driver == null) {
                throw new Exception("Impossible to get WebDriver instance: it is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return driver;
    }

    public static WebDriver initDriver() {

        File file = new File("src/main/resources/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        driver = new ChromeDriver();

        return driver;
    }

}
