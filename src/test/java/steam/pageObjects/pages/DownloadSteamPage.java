package steam.pageObjects.pages;

import framework.baseElement.Button;
import framework.utils.FileManager;
import org.openqa.selenium.By;
import steam.pageObjects.baseComponents.Header;

public class DownloadSteamPage extends BaseSteamPage {

    private static String pageLocator = "//a[@class='about_install_steam_link']";
    private String commonLocatorForDownloadButton = "//a[@class='about_install_steam_link'][contains(text(),'%s')]";

    public DownloadSteamPage() {
        super(By.xpath(pageLocator), "DownloadSteamPage");
    }

    public void downloadSteam() {
        Button btnDownloadSteam = new Button(By.xpath(String.format(commonLocatorForDownloadButton, Header.languageReader.getProperty("btnDownloadSteam"))), "Download Steam button");
        btnDownloadSteam.click();
    }

    public void waitSteamToBeDownloaded() {
        FileManager manager = new FileManager();
        manager.waitForFileDownloaded();
    }
}
