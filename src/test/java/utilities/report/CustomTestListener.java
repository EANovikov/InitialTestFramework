package utilities.report;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import utilities.driver.DriverFactory;
import utilities.driver.WebDriverFactory;

public class CustomTestListener extends TestListenerAdapter {

    private Logger log = LoggerFactory.getLogger(CustomTestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Test class started: " + result.getTestClass().getName());
        log.info("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test SUCCESS: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        makeScreenshot();
        log.info("Test FAILED: " + result.getName());
        if (result.getThrowable()!=null) {
            result.getThrowable().printStackTrace();
        }
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] makeScreenshot() {
        return ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
        //return ((TakesScreenshot) WebDriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.BYTES);
    }

}
