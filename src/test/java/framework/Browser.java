package framework;

import framework.utils.PropertyReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Browser {

    public static Browser instance;
    public static WebDriver driver;
    public static PropertyReader configReader = new PropertyReader("config.properties");

    public static Browser getInstance() {
        if (instance == null) {
            driver = DriverFactory.setUp();
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

    public WebDriver getDriver(){
        return driver;
    }

    public void switchBrowserWindow(int a){
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(a));
    }


    public void waitPageToLoad(){
        WebDriverWait wait = new WebDriverWait(driver,Long.parseLong(configReader.getProperty("webDriverWait")));
        try {
            wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>(){
                public Boolean apply(final WebDriver driver){
                    if (!(driver instanceof JavascriptExecutor)){
                        return true;
                    }
                    Object result = ((JavascriptExecutor) driver)
                            .executeScript("return document['readyState'] ? 'complete' == document.readyState : true");
                    return result instanceof Boolean && (Boolean) result;
                }
            });
        } catch (Exception e) {
        }
    }
}
