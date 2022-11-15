package steam.pages;

import framework.baseElement.Label;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.Assert;

@Log4j2
public class GamePage extends BaseSteamPage {

    private static String pageLocator = "//div[@class='page_content_ctn']";
    private Label lblGameTitle = new Label(By.xpath("//div[@id='appHubAppName']"), "Title of the Game");

    public GamePage() {
        super(By.xpath(pageLocator), "GamePage");
    }

    public void checkingGameTitle() {
        log.info("Check the title of the game");
        Assert.assertEquals(lblGameTitle.getText(), GenreActionPage.randomGameWithTheHighestDiscount, "Game Titles are not equal");
    }
}
