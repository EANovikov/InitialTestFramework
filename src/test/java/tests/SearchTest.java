package tests;

import utilities.data.TestData;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import steps.SearchResultsSteps;
import steps.SearchSteps;

public class SearchTest extends BaseTest{

    @Test (dataProvider = "TestData")
    @Severity(SeverityLevel.BLOCKER)
    @Description("TC01: verify basic search functionality")
    public void searchByKeywordsAndVerifyUrlInTop(TestData testData) {
        new SearchSteps().doSearchWithKeyword(testData.getSearchText());
        new SearchResultsSteps().verifyThatValueIsOnTop(testData.getSearchResult());
 }

    @Test (dataProvider = "TestData")
    @Severity(SeverityLevel.BLOCKER)
    @Description("TC02: verify basic search functionality")
    public void searchByKeywordsAndCheckThatFirstWordIsFoundInAllRows(TestData testData) {
        new SearchSteps().doSearchWithKeyword(testData.getSearchText());
        new SearchResultsSteps().verifyThatAllRowsContain(testData.getSearchResult());
    }

}


