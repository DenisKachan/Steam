package steam.pageObjects.pages;

import framework.BasePage;
import org.openqa.selenium.By;
import steam.pageObjects.baseComponents.Header;

public class BaseSteamPage extends BasePage {

    protected BaseSteamPage(By locator, String name) {
        super(locator, name);
    }

    public Header getHeader(){
        return new Header();
    }
}
