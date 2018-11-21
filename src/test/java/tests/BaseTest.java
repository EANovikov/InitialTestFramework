package tests;

import utilities.config.ConfigReader;
import utilities.data.ExcelFileReader;
import utilities.data.TestData;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.driver.DriverFactory;
import utilities.driver.WebDriverFactory;
import utilities.report.CustomAppender;
import utilities.report.CustomTestListener;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

@Listeners({CustomTestListener.class})
public abstract class BaseTest {

    private static WebDriver driver;
    private final long TIMEOUT = 80000;
    private String baseUrl = System.getProperty("url");
    private Logger logger;
    private CustomAppender appender;

    BaseTest() {
        baseUrl = (baseUrl == null) ? ConfigReader.getBaseUrl() : baseUrl;
    }

    @BeforeMethod(alwaysRun = true, timeOut = TIMEOUT)
    public void setUp(Method testContext, Object[] testArguments) {
        logger = setUp(testContext.getName());
        appender = (CustomAppender) logger.getAppender("CustomAppender");
        //driver = WebDriverFactory.getInstance().getDriver(baseUrl);
        driver = DriverFactory.initDriver(baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(Method textContext, ITestResult result) {

        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                logger.info("=======" + textContext.getName() + " PASSED =======\n");
                break;
            case ITestResult.FAILURE:
                logger.info("=======" + textContext.getName() + " FAILED =======\n");
                break;
            case ITestResult.SKIP:
                logger.info("=======" + textContext.getName() + " SKIPPED =======\n");
                break;
            default:
                System.out.println("=======" + textContext.getName() + " BROKEN =======\n");
                }
         // WebDriverFactory.getInstance().closeDriver();
        DriverFactory.closeDriver();
        logger.removeAllAppenders();
    }

    @DataProvider(name = "TestData"/*, parallel = true*/)
    public Object[][] getTestCaseData(Method testContext) throws InterruptedException {
        List<TestData> testData = ExcelFileReader.readTestData(testContext.getName());
        return testData.parallelStream()
                .filter(x -> x.getTestCaseName().equalsIgnoreCase(testContext.getName()))
                .map(x -> new Object[]{x})
                .collect(Collectors.toList())
                .toArray(new Object[0][0]);
    }

    private Logger setUp(String name) {
        logger = Logger.getLogger(name);
        CustomAppender appender = new CustomAppender();
        logger.addAppender(appender);
        return logger;
    }

}
