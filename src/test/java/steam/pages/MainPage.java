package steam.pages;

import framework.baseElement.Label;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

@Log4j2
public class MainPage extends BaseSteamPage {

    private static String pageLocator = "//div[@class='logo']";
    private String commonLocatorForMenuSection = "//div[@class='store_nav']//following::a[@class='pulldown_desktop'][contains(text(),'%s')]";
    private String commonLocatorForGenre = "//div[@id='genre_flyout']//following::a[contains(text(),'%s')]";

    public MainPage() {
        super(By.xpath(pageLocator), "MainPage");
    }

    public void navigateMenu(String menuItem, String subMenuItem) {
        log.info("Choose menu section");
        Label lblMenuSection = new Label(By.xpath(String.format(commonLocatorForMenuSection, Header.languageReader.getProperty(menuItem))), "Menu item label");
        lblMenuSection.mouseMoveToElement();
        log.info("Choose menu subsection");
        Label lblGenre = new Label(By.xpath(String.format(commonLocatorForGenre, Header.languageReader.getProperty(subMenuItem))), "SubMenu item label");
        lblGenre.clickAndWait();
    }
}
