package framework;

import framework.baseElement.Label;
import org.openqa.selenium.By;

public abstract class BasePage extends BaseEntity{

    protected By titleLocator;

    protected BasePage (final By locator){
        initialize(locator);
        assertIsOpen();
    }

    public void initialize(final By locator){
        titleLocator = locator;
    }

    public void assertIsOpen(){
        Label element = new Label(titleLocator);
        element.waitElementToBePresent();
    }
}
