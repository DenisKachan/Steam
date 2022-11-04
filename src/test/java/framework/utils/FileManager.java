package framework.utils;

import framework.BaseEntity;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import java.io.File;
import java.time.Duration;


public class FileManager extends BaseEntity {

    private final File file = new File("C:\\\\Users\\\\ASUS\\\\Downloads\\\\SteamSetup.exe");

    public void checkAndDeleteFile(){
        if (file.exists()){
            file.delete();}
    }

    public void waitForFileDownloaded() {
        FluentWait<WebDriver> wait = new FluentWait<>(browser.getDriver())
                .withTimeout(Duration.ofSeconds(50))
                .pollingEvery(Duration.ofMillis(500));
        wait.until((webDriver) -> file.canRead());
    }
}
