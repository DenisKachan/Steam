package framework;

import framework.baseElement.Label;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.Assert;

@Log4j2
public abstract class BasePage {

    protected By titleLocator;
    protected String pageName;
    private Browser browser = new Browser();

    protected BasePage(final By locator, final String name) {
        initialize(locator, name);
        assertIsOpen();
    }

    public void initialize(final By locator, final String name) {
        titleLocator = locator;
        pageName = name;
    }

    public void assertIsOpen() {
        browser.waitPageToLoad();
        Label element = new Label(titleLocator, pageName);
        log.info("Check if the page {} is loaded", pageName);
        Assert.assertTrue(element.elementIsPresent(), "Page is not loaded");
    }
}
