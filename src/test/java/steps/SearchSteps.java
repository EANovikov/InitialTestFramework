package steps;

import pages.*;

public class SearchSteps {
    private SearchPage searchPage = new SearchPage();

    public void doSearchWithKeyword(String keyword){
        searchPage.fillTheSearchField(keyword);
        searchPage.pressEnter();
    }

    public void clickVoiceSearchDeclineAlert(){
        searchPage.pressVoiceSearchCloseAlert();
    }

}
