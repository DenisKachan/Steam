package steam.pages;

import framework.BasePage;
import framework.baseElement.Label;
import org.openqa.selenium.By;

public class BaseSteamPage extends BasePage {

    private static String pageLocator = "//div[@class='logo']";
    private String commonLocatorForMenuSection = "//div[@class='store_nav']//following::a[@class='pulldown_desktop'][contains(text(),'%s')]";
    private String commonLocatorForGenre = "//div[@id='genre_flyout']//following::a[contains(text(),'%s')]";

    public BaseSteamPage() {
        super(By.xpath(pageLocator),"BaseSteamPage");
    }

    public void navigateMenu(String menuItem, String subMenuItem){
        Label lblMenuSection = new Label(By.xpath(String.format(commonLocatorForMenuSection, HeaderPage.languageReader.getProperty(menuItem))),"Menu item label");
        lblMenuSection.mouseMoveToElement();
        Label lblGenre = new Label(By.xpath(String.format(commonLocatorForGenre, HeaderPage.languageReader.getProperty(subMenuItem))),"SubMenu item label");
        lblGenre.clickAndWait();
    }
}
