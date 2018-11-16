package tests;

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

    public static WebDriver getDriver(){
        return driver;
    }

    @BeforeMethod (alwaysRun = true)
    public void setUp(Method testContext, Object [] testArguments) {
        driver = WebDriverFactory.initDriver();
        //driver.manage().window().maximize();
        driver.navigate().to("https://www.google.com");
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown(Method textContext, ITestResult result) {
        driver.quit();
        switch(result.getStatus()){
            case ITestResult.SUCCESS:
                System.out.println("======="+textContext.getName() + " PASSED =======\n");
                break;
            case ITestResult.FAILURE:
                System.out.println("======="+textContext.getName() + " FAILED =======\n");
                break;
            case ITestResult.SKIP:
                System.out.println("======="+textContext.getName() + " SKIPPED =======\n");
                break;
            default:
                System.out.println("======="+textContext.getName() + " UNKNOWN =======\n");
        }
    }

    @DataProvider(name = "TestData")
    public Object[][] getTestCaseData(Method testContext){
        List<TestData> testData = ExcelFileReader.readTestData(testContext.getName());
        return testData.parallelStream()
                .filter(x -> x.getTestCaseName().equalsIgnoreCase(testContext.getName()))
                .map(x -> new Object[]{x})
                .collect(Collectors.toList())
                .toArray(new Object[0][0]);
    }

}
