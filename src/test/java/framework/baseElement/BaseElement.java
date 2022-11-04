package framework.baseElement;

import framework.BaseEntity;
import framework.utils.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseElement extends BaseEntity {

    protected By locator;
    protected WebElement element;
    private final PropertyReader configReader = new PropertyReader("config.properties");

    public BaseElement (final By loc){
        locator = loc;
    }

    public WebElement getElement(){
        waitElementToBePresent();
        return element;
    }

    public List<WebElement> getListOfElements(){
        waitElementToBePresent();
        return element.findElements(locator);
    }

    public String getText(){
        waitElementToBePresent();
        return element.getText();
    }

    public void sendKeys(String key){
        waitElementToBePresent();
        element.sendKeys(key);
    }

    public void click(){
        waitElementToBePresent();
        mouseMoveToElement();
        if (browser.getDriver() instanceof JavascriptExecutor){
            ((JavascriptExecutor)browser.getDriver()).executeScript("arguments[0].style.border='3px solid red'",element);
        }
        element.click();
    }

    public void clickAndWait(){
        click();
        waitPageToLoad();
    }

    public void mouseMoveToElement(){
        waitElementToBePresent();
        Actions builder = new Actions(browser.getDriver());
        JavascriptExecutor js = (JavascriptExecutor) browser.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        builder.moveToElement(element).perform();
    }

    public boolean isPresent(){
        WebDriverWait wait = new WebDriverWait(browser.getDriver(),Long.parseLong(configReader.getProperty("webDriverWait")));
        browser.getDriver().manage().timeouts().implicitlyWait(Long.parseLong(configReader.getProperty("defaultWait")), TimeUnit.SECONDS);
        try {
            wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>(){
                public Boolean apply(final WebDriver driver){
                    try {
                        List<WebElement> list = driver.findElements(locator);
                        for (WebElement el : list) {
                            if (el instanceof WebDriver && el.isDisplayed()){
                                element = el;
                                return element.isDisplayed();
                            }
                        }
                        element = driver.findElement(locator);
                    } catch (Exception e) {
                        return false;
                    }
                    return element.isDisplayed();
                }
            });
        } catch (Exception e) {
            return false;
        }
        try {
            browser.getDriver().manage().timeouts().implicitlyWait(Long.parseLong(configReader.getProperty("defaultWait")), TimeUnit.SECONDS);
            return element.isDisplayed();
        } catch (Exception e){}
        return false;
    }

    public void waitElementToBePresent(){
        isPresent();
        Assert.assertTrue(element.isDisplayed());
    }

    public void waitPageToLoad(){
        WebDriverWait wait = new WebDriverWait(browser.getDriver(),Long.parseLong(configReader.getProperty("webDriverWait")));
        try {
            wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>(){
                public Boolean apply(final WebDriver driver){
                    if (!(driver instanceof JavascriptExecutor)){
                        return true;
                    }
                    Object result = ((JavascriptExecutor) driver)
                            .executeScript("return document['readyState'] ? 'complete' == document.readyState : true");
                    return result instanceof Boolean && (Boolean) result;
                }
            });
        } catch (Exception e) {
        }
    }
}
