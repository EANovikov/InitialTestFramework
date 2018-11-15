package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class SearchResultPage extends BasePage {

    @FindBy(xpath = "//cite[@class='iUh30']")
    WebElement topSearchResult;

    @FindBy(xpath = "//cite[@class='iUh30']")
    List<WebElement> searchResultURLs;

    public SearchResultPage() {
        super();
    }

    public void assertThatExpectedValueIsOnSearchTop(String expectedValue) {
        wait.until(ExpectedConditions.visibilityOf(topSearchResult));
        assertEquals(searchResultURLs.get(0).getText(), expectedValue, expectedValue + " is not the first result!");
}

    boolean waitForElementWithTimeout(By by, int timeoutInSeconds) throws InterruptedException {
        List<WebElement> elements = driver.findElements(by);

        for (int i = 0; (i < timeoutInSeconds) && (elements.size() == 0); i++) {
            Thread.sleep(1000);
        }
        searchResultURLs.get(1).isDisplayed();
        return elements.size() > 0;
    }

}