package framework.utils;

import framework.Browser;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import java.io.File;
import java.time.Duration;

@Log4j2
public class FileManager {

    private Browser browser = new Browser();
    private static PropertyReader configReader = new PropertyReader("config.properties");
    private final File file = new File(System.getProperty("user.dir") + configReader.getProperty("downloadPath").concat(configReader.getProperty("fileToDownload")));

    public void checkAndDeleteFile() {
        if (file.exists()) {
            log.info("Delete file {}", file);
            file.delete();
        }
    }

    public void waitForFileDownloaded() {
        log.info("Wait file {} to be downloaded", file);
        FluentWait<WebDriver> wait = new FluentWait<>(browser.getDriver())
                .withTimeout(Duration.ofSeconds(Long.parseLong(configReader.getProperty("fileToDownloadWait"))))
                .pollingEvery(Duration.ofMillis(Long.parseLong(configReader.getProperty("fileToDownloadPolling"))));
        wait.until((webDriver) -> file.canRead());
        Assert.assertTrue(file.canRead(), "File is not downloaded");
    }
}
