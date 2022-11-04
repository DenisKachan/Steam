package framework;

import framework.utils.FileManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseEntity {

    protected static Browser browser;

    @BeforeMethod
    public void before(){
        FileManager manager = new FileManager();
        manager.checkAndDeleteFile();
        browser = Browser.getInstance();
        browser.navigateToURL();
    }

    @AfterMethod
    public void after(){
        if (browser.isBrowserAlive()){
            browser.exit();
        }
    }
}
