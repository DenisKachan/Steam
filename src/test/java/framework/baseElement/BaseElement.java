package framework.baseElement;


import framework.Browser;
import framework.utils.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseElement {

    protected By locator;
    protected String nameElement;
    protected WebElement element;
    private Browser browser = new Browser();
    private final PropertyReader configReader = new PropertyReader("config.properties");

    public BaseElement (final By loc, final String name){
        locator = loc;
        nameElement = name;
    }

    public List<WebElement> getListOfElements(){
        elementIsPresent();
        return element.findElements(locator);
    }

    public String getText(){
        elementIsPresent();
        return element.getText();
    }

    public void sendKeys(String key){
        elementIsPresent();
        element.sendKeys(key);
    }

    public void click(){
        elementIsPresent();
        mouseMoveToElement();
        if (Browser.driver instanceof JavascriptExecutor){
            ((JavascriptExecutor)Browser.driver).executeScript("arguments[0].style.border='3px solid red'",element);
        }
        element.click();
    }

    public void clickAndWait(){
        click();
        browser.waitPageToLoad();
    }

    public void mouseMoveToElement(){
        elementIsPresent();
        Actions builder = new Actions(Browser.driver);
        JavascriptExecutor js = (JavascriptExecutor) Browser.driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        builder.moveToElement(element).perform();
    }

    public boolean elementIsPresent(){
        Browser.driver.manage().timeouts().implicitlyWait(Long.parseLong(configReader.getProperty("defaultWait")), TimeUnit.SECONDS);
        try {
            element = Browser.driver.findElement(locator);
        } catch (Exception e) {
            return false;
        }
        return element.isDisplayed();
    }
}
