package framework.baseElement;

import framework.Browser;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

@Log4j2
public class BaseElements {

    protected String nameElement;
    protected List<WebElement> webElementList;

    public BaseElements(List<WebElement> webElements, String name) {
        webElementList = webElements;
        nameElement = name;
    }

    public WebElement getElementFromList(int index) {
        log.info("Get the element number {} from the list", index);
        return webElementList.get(index);
    }

    public boolean elementFromListDisplayed(int index) {
        try {
            log.info("Check if the element number {} is displayed", index);
            getElementFromList(index).isDisplayed();
        } catch (Exception e) {
            log.error("Element from list is not displayed");
            e.printStackTrace();
        }
        return getElementFromList(index).isDisplayed();
    }

    public void mouseMoveToTheElementFromList(int index) {
        Actions builder = new Actions(Browser.driver);
        log.info("Move mouse to the element number {} from list", index);
        builder.moveToElement(getElementFromList(index)).perform();
    }
}
