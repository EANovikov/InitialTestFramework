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
    @FindBy(id = "cnsd")
    WebElement remindLater;

    @FindBy(name = "q")
    WebElement searchTextField;

    @FindBy(id = "lst-ib")
    WebElement searchField;

    @FindBy(id = "gsri_ok0")
    WebElement voiceSearchButton;

    public SearchPage(){super();}

    public void fillTheSearchField(String keyword) {

        wait.until(ExpectedConditions.visibilityOf(searchTextField));
        if(remindLater.isDisplayed()){
            remindLater.click();
        }
        searchTextField.sendKeys(keyword);
    }
    public void pressEnter() {
        searchTextField.sendKeys(Keys.RETURN);
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
