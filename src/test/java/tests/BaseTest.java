package tests;

import dataManagment.ExcelFileReader;
import dataManagment.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseTest {

    private static WebDriver driver;

    public static WebDriver getDriver(){
        return driver;
    }

    @BeforeMethod
    public void setUp(Method testContext, Object [] testArguments) {
        File file = new File("src/main/resources/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        driver = new ChromeDriver();
        //driver.manage().window().maximize();
        driver.navigate().to("https://www.google.com");
    }

    @AfterMethod
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
