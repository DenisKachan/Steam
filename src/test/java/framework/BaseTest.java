package framework;

import framework.utils.FileManager;
import framework.utils.PropertyReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    protected static Browser browser;
    private final PropertyReader configReader = new PropertyReader("config.properties");

    @BeforeMethod
    public void before(){
        FileManager manager = new FileManager();
        manager.checkAndDeleteFile();
        browser = Browser.getInstance();
    }

    @AfterMethod
    public void after(){
        if (browser.isBrowserAlive()){
            browser.exit();
        }
    }

    public void navigateToURL(String url){
        Browser.driver.get(configReader.getProperty(url));
    }
}
