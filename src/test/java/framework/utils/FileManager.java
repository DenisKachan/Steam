package framework.utils;


import framework.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import java.io.File;
import java.time.Duration;


public class FileManager {

    private Browser browser = new Browser();
    private static PropertyReader configReader = new PropertyReader("config.properties");
    private final File file = new File(configReader.getProperty("downloadPath").concat(configReader.getProperty("fileToDownload")));

    public void checkAndDeleteFile(){
        if (file.exists()){
            file.delete();}
    }

    public void waitForFileDownloaded() {
        FluentWait<WebDriver> wait = new FluentWait<>(browser.getDriver())
                .withTimeout(Duration.ofSeconds(Long.parseLong(configReader.getProperty("fileToDownloadWait"))))
                .pollingEvery(Duration.ofMillis(Long.parseLong(configReader.getProperty("fileToDownloadPolling"))));
        wait.until((webDriver) -> file.canRead());
    }
}
