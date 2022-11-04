package steam.pages;

import framework.baseElement.Button;
import framework.utils.FileManager;
import org.openqa.selenium.By;

public class DownloadSteamPage extends BaseSteamPage{

    private static String formLocator = "//a[@class='about_install_steam_link']";
    private final String commonLocatorForDownloadButton = "//a[@class='about_install_steam_link'][contains(text(),'%s')]";

    public DownloadSteamPage() {
        super(By.xpath(formLocator));
    }

    public void downloadSteam(){
        Button btnDownloadSteam = new Button(By.xpath(String.format(commonLocatorForDownloadButton,languageReader.getProperty("btnDownloadSteam"))));
        btnDownloadSteam.click();
    }

    public void waitSteamToBeDownloaded(){
        FileManager manager = new FileManager();
        manager.waitForFileDownloaded();
    }
}
