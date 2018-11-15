package tests;

import dataManagment.TestData;
import org.testng.annotations.Test;
import steps.SearchResultsSteps;
import steps.SearchSteps;



public class SearchTest extends BaseTest{

    @Test (dataProvider = "TestData")
    public void searchByKeywordSeleniumHaveToFindSeleniumhqOrgInTop(TestData testData) {
        new SearchSteps().doSearchWithKeyword(testData.getSearchText());
        new SearchResultsSteps().verifyThatValueIsOnTop(testData.getSearchResult());
 }

}


