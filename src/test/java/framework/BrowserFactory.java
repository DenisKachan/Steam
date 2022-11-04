package framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFactory {


    public static WebDriver setUp(final String browserType) {
        switch (browserType) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("disable-popup-blocking","start-maximized");

                return new ChromeDriver(options);
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                 FirefoxOptions firefoxOptions = new FirefoxOptions();
                 firefoxOptions.addArguments("browser.helperApps.neverAsk.saveToDisk");
                return new FirefoxDriver(firefoxOptions);
            }
            default -> throw new IllegalArgumentException();
        }
    }
}
