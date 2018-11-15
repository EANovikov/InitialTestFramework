package steps;

import pages.*;

public class SearchResultsSteps {
    private SearchResultPage searchResultsPage = new SearchResultPage();

    public void verifyThatValueIsOnTop(String expectedValue){
        searchResultsPage.assertThatExpectedValueIsOnSearchTop(expectedValue);
    }

}
