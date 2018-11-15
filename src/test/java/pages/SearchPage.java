package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class SearchPage extends BasePage {
    @FindBy(id = "lst-ib")
    WebElement searchField;

    @FindBy(id = "gsri_ok0")
    WebElement voiceSearchButton;

    public SearchPage(){super();}

    public void fillTheSearchField(String keyword) {
        Actions builder = new Actions(driver);
        builder.click().build().perform();
        builder.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN)
                .build().perform();
        searchField.sendKeys(keyword);
    }
    public void pressEnter() {
        searchField.sendKeys(Keys.RETURN);
    }

    public void pressVoiceSearchCloseAlert() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(voiceSearchButton));
        voiceSearchButton.click();
        wait.until(ExpectedConditions.alertIsPresent());
        Actions actions = new Actions(driver);
        actions.click().sendKeys("something").build();
    }


}
