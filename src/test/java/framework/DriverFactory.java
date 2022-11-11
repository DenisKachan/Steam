package framework;

import framework.utils.PropertyReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    private static PropertyReader configReader = new PropertyReader("config.properties");

    public static WebDriver setUp() {
        switch (configReader.getProperty("browser")) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("disable-popup-blocking","start-maximized");
                options.addArguments("--safebrowsing-disable-download-protection");
                options.addArguments("safebrowsing-disable-extension-blacklist");
                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("download.default_directory", configReader.getProperty("downloadPath"));
                prefs.put("download.prompt_for_download", false);
                prefs.put("safebrowsing.enabled", true);
                options.setExperimentalOption("prefs", prefs);
                return new ChromeDriver(options);
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addPreference("browser.download.folderList", 2);
                firefoxOptions.addPreference("browser.download.dir", configReader.getProperty("downloadPath"));
                firefoxOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-gzip");
                 return new FirefoxDriver(firefoxOptions);
            }
            default -> throw new IllegalArgumentException();
        }
    }
}
