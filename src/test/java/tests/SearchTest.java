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
    public void searchByKeywordSeleniumHaveToFindSeleniumhqOrgInTop(TestData testData) {
        new SearchSteps().doSearchWithKeyword(testData.getSearchText());
        new SearchResultsSteps().verifyThatValueIsOnTop(testData.getSearchResult());
 }

}


