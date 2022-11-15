package steam.pages;

import framework.baseElement.Button;
import framework.utils.FileManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

@Log4j2
public class DownloadSteamPage extends BaseSteamPage {

    private static String pageLocator = "//a[@class='about_install_steam_link']";
    private String commonLocatorForDownloadButton = "//a[@class='about_install_steam_link'][contains(text(),'%s')]";

    public DownloadSteamPage() {
        super(By.xpath(pageLocator), "DownloadSteamPage");
    }

    public void downloadSteam() {
        log.info("Download Steam installer");
        Button btnDownloadSteam = new Button(By.xpath(String.format(commonLocatorForDownloadButton, Header.languageReader.getProperty("btnDownloadSteam"))), "Download Steam button");
        btnDownloadSteam.click();
    }

    public void waitSteamToBeDownloaded() {
        log.info("Wait Steam installer to be downloaded");
        FileManager manager = new FileManager();
        manager.waitForFileDownloaded();
    }
}
