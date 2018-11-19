package pages;

import config.ConfigReader;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.driver.WebDriverFactory;
import utilities.report.CustomAppender;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class BasePage extends PageFactory {

    WebDriver driver;
    WebDriverWait wait;
    Actions builder;
    JavascriptExecutor js;
    Logger logger;
    CustomAppender appender;

    public BasePage() {
        this.driver = WebDriverFactory.getInstance().getDriver();
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, ConfigReader.getElementWaitTimeout());
        builder = new Actions(driver);
        js = (JavascriptExecutor) driver;
    }

    public void pasteTextFromClipboard(WebElement element, String text) {
        //copy text to memory buffer
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        StringSelection strSel = new StringSelection(text);
        clipboard.setContents(strSel, null);
        //paste it with selenium actions:
        Actions builder = new Actions(driver);
        builder
                .contextClick(element).sendKeys(Keys.CONTROL, "v")
                .build().perform();
    }

}