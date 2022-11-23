package framework;

import framework.utils.FileManager;
import framework.utils.PropertyReader;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@Log4j2
public abstract class BaseTest {

    protected static Browser browser;
    private final PropertyReader configReader = new PropertyReader("config.properties");

    @BeforeMethod
    public void before() {
        FileManager manager = new FileManager();
        manager.checkAndDeleteFile();
        browser = Browser.getInstance();
        browser.navigateToURL("baseUrl");
    }

    @AfterMethod
    public void after() {
        if (browser.isBrowserAlive()) {
            browser.exit();
        }
    }
}
