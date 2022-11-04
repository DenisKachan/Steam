package framework;

import framework.utils.PropertyReader;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Browser {

    private static Browser instance;
    private static WebDriver driver;
    private static final PropertyReader configReader = new PropertyReader("config.properties");

    private Browser() {
    }

    public static Browser getInstance() {
        if (instance == null) {
            driver = BrowserFactory.setUp(configReader.getProperty("browser"));
            driver.manage().timeouts().implicitlyWait(Long.parseLong(configReader.getProperty("defaultWait")), TimeUnit.SECONDS);
            instance = new Browser();
        }
        return instance;
    }

    public boolean isBrowserAlive(){
        return instance !=null;
    }

    public void exit(){
        driver.quit();
        instance = null;
    }

    public void navigateToURL(){
        driver.get(configReader.getProperty("baseUrl"));
    }

    public WebDriver getDriver(){
        return driver;
    }

    public void switchToTheNextBrowserWindow(){
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    public void switchToThePreviousWindow(){
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }
}
