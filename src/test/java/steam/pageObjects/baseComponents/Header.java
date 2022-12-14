package steam.pageObjects.baseComponents;

import framework.BasePage;
import framework.baseElement.Button;
import framework.baseElement.Label;
import framework.utils.PropertyReader;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class Header extends BasePage {

    private static String pageLocator = "//div[@class='logo']";
    private String commonLocatorForLanguage = "//span[@id='language_pulldown']/following::a[contains(text(),'%s')]";
    private String commonLocatorForInstallSteam = "//div[@id='global_action_menu']/descendant::a[contains(text(),'%s')]";
    private Label lblLanguageList = new Label(By.xpath("//span[@id='language_pulldown']"), "Language list label");
    public static PropertyReader languageReader;

    public Header() {
        super(By.xpath(pageLocator), "Header");
    }

    public void chooseLanguage(String language) {
        lblLanguageList.click();
        Label lblLanguage = new Label(By.xpath(String.format(commonLocatorForLanguage, language)), "Language label");
        if (lblLanguage.elementIsPresent()) {
            lblLanguage.clickAndWait();
        }
        switch (language) {
            case "Русский" -> languageReader = new PropertyReader("ru.properties");
            case "English" -> languageReader = new PropertyReader("en.properties");
        }
    }

    public void clickInstallSteam() {
        Button btnInstallSteam = new Button(By.xpath(String.format(commonLocatorForInstallSteam, languageReader.getProperty("installSteam"))), "Install Steam button");
        btnInstallSteam.clickAndWait();
    }
}
