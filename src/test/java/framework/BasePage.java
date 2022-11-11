package framework;

import framework.baseElement.Label;
import org.openqa.selenium.By;
import org.testng.Assert;

public abstract class BasePage {

    protected By titleLocator;
    protected String pageName;
    private Browser browser = new Browser();

    protected BasePage (final By locator, final String name){
        initialize(locator,name);
        assertIsOpen();
    }

    public void initialize(final By locator, final String name){
        titleLocator = locator;
        pageName = name;
    }

    public void assertIsOpen(){
        browser.waitPageToLoad();
        Label element = new Label(titleLocator,pageName);
        Assert.assertTrue(element.elementIsPresent(),"Page is not loaded");
    }
}
