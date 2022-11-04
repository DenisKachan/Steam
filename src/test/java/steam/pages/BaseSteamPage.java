package steam.pages;

import framework.BasePage;
import framework.baseElement.Button;
import framework.baseElement.Label;
import framework.utils.PropertyReader;
import org.openqa.selenium.By;

public class BaseSteamPage extends BasePage {

    private static String formLocator = "//div[@class='logo']";
    private final String commonLocatorForLanguage = "//span[@id='language_pulldown']/following::a[contains(text(),'%s')]";
    private final String commonLocatorForMenuSection = "//div[@class='store_nav']//following::a[@class='pulldown_desktop'][contains(text(),'%s')]";
    private final String commonLocatorForGenre = "//div[@id='genre_flyout']//following::a[contains(text(),'%s')]";
    private final String commonLocatorForInstallSteam = "//div[@id='global_action_menu']/descendant::a[contains(text(),'%s')]";
    private final Label lblLanguageList = new Label(By.xpath("//span[@id='language_pulldown']"));
    protected static PropertyReader languageReader;

    protected BaseSteamPage(By locator) {
        super(locator);
    }

    public BaseSteamPage() {
        super(By.xpath(formLocator));
    }

    public void chooseLanguage(String language){
        lblLanguageList.click();
        Label lblLanguage = new Label(By.xpath(String.format(commonLocatorForLanguage, language)));
        if (lblLanguage.isPresent())
        {lblLanguage.clickAndWait();}
        switch (language) {
            case "Русский" -> languageReader = new PropertyReader("ru.properties");
            case "English" -> languageReader = new PropertyReader("en.properties");
        }
    }

    public void navigateMenu(String menuItem, String subMenuItem){
        Label lblMenuSection = new Label(By.xpath(String.format(commonLocatorForMenuSection, languageReader.getProperty(menuItem))));
        lblMenuSection.mouseMoveToElement();
        Label lblGenre = new Label(By.xpath(String.format(commonLocatorForGenre, languageReader.getProperty(subMenuItem))));
        lblGenre.clickAndWait();
    }

    public void clickInstallSteam(){
        Button btnInstallSteam = new Button(By.xpath(String.format(commonLocatorForInstallSteam,languageReader.getProperty("installSteam"))));
        btnInstallSteam.clickAndWait();
    }
}
