package steps;

import io.qameta.allure.Step;
import pages.*;

public class SearchResultsSteps {

    private SearchResultPage searchResultsPage = new SearchResultPage();

    @Step("Verify that expected value {0} has been displayed on result page")
    public void verifyThatValueIsOnTop(String expectedValue) {
        searchResultsPage.assertThatExpectedValueIsOnSearchTop(expectedValue);
    }

}
