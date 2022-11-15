package framework.baseElement;

import framework.Browser;
import framework.utils.PropertyReader;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Log4j2
public class BaseElement {

    protected By locator;
    protected String nameElement;
    protected WebElement element;
    private Browser browser = new Browser();
    private final PropertyReader configReader = new PropertyReader("config.properties");

    public BaseElement(final By loc, final String name) {
        locator = loc;
        nameElement = name;
    }

    public List<WebElement> getListOfElements() {
        elementIsPresent();
        log.info("Get a list of elements {}", nameElement);
        return element.findElements(locator);
    }

    public String getText() {
        elementIsPresent();
        log.info("Get text from the element {}", nameElement);
        return element.getText();
    }

    public void sendKeys(String key) {
        elementIsPresent();
        log.info("Send {} to the element", key);
        element.sendKeys(key);
    }

    public void click() {
        elementIsPresent();
        mouseMoveToElement();
        if (Browser.driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) Browser.driver).executeScript("arguments[0].style.border='3px solid red'", element);
        }
        log.info("Click {} element", nameElement);
        element.click();
    }

    public void clickAndWait() {
        click();
        browser.waitPageToLoad();
    }

    public void mouseMoveToElement() {
        elementIsPresent();
        Actions builder = new Actions(Browser.driver);
        JavascriptExecutor js = (JavascriptExecutor) Browser.driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        log.info("Move mouse to the element {}", nameElement);
        builder.moveToElement(element).perform();
    }

    public boolean elementIsPresent() {
        Browser.driver.manage().timeouts().implicitlyWait(Long.parseLong(configReader.getProperty("defaultWait")), TimeUnit.SECONDS);
        try {
            log.info("Find an element {}", nameElement);
            element = Browser.driver.findElement(locator);
        } catch (Exception e) {
            log.error("Element {} is not present",nameElement);
        }
        return element.isDisplayed();
    }
}
