package steps;

import io.qameta.allure.Step;
import pages.*;

public class SearchSteps {
    private SearchPage searchPage = new SearchPage();

    @Step("Execute search with keyword: {0}")
    public void doSearchWithKeyword(String keyword){
        searchPage.fillTheSearchField(keyword);
        searchPage.pressEnter();
    }

    public void clickVoiceSearchDeclineAlert(){
        searchPage.pressVoiceSearchCloseAlert();
    }

}
