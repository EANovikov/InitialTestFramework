package tests;

import config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import utilities.data.ExcelFileReader;
import utilities.data.TestData;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.driver.WebDriverFactory;
import utilities.report.CustomTestListener;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

@Listeners({CustomTestListener.class})
public abstract class BaseTest {

    private static WebDriver driver;
    private final long TIMEOUT = 80000;
    private String baseUrl = System.getProperty("url");

    BaseTest() {
        baseUrl = (baseUrl == null) ? ConfigReader.getBaseUrl() : baseUrl;
    }

    @BeforeMethod(alwaysRun = true, timeOut = TIMEOUT)
    public void setUp(Method testContext, Object[] testArguments) {
        driver = WebDriverFactory.getInstance().getDriver(baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(Method textContext, ITestResult result) {
        WebDriverFactory.getInstance().closeDriver();
        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                System.out.println("=======" + textContext.getName() + " PASSED =======\n");
                break;
            case ITestResult.FAILURE:
                System.out.println("=======" + textContext.getName() + " FAILED =======\n");
                break;
            case ITestResult.SKIP:
                System.out.println("=======" + textContext.getName() + " SKIPPED =======\n");
                break;
            default:
                System.out.println("=======" + textContext.getName() + " INCOMPLETE =======\n");
        }
    }

    @DataProvider(name = "TestData", parallel = true)
    public Object[][] getTestCaseData(Method testContext) throws InterruptedException {
        List<TestData> testData = ExcelFileReader.readTestData(testContext.getName());
        return testData.parallelStream()
                .filter(x -> x.getTestCaseName().equalsIgnoreCase(testContext.getName()))
                .map(x -> new Object[]{x})
                .collect(Collectors.toList())
                .toArray(new Object[0][0]);
    }

}
