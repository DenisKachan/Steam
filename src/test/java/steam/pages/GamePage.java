package steam.pages;

import framework.baseElement.Label;
import org.openqa.selenium.By;
import org.testng.Assert;

public class GamePage extends BaseSteamPage {

    private static String pageLocator = "//div[@class='page_content_ctn']";
    private Label lblGameTitle = new Label(By.xpath("//div[@id='appHubAppName']"), "Title of the Game");

    public GamePage() {
        super(By.xpath(pageLocator), "GamePage");
    }

    public void checkingGameTitle() {
        Assert.assertEquals(lblGameTitle.getText(), GenreActionPage.randomGameWithTheHighestDiscount, "Game Titles are not equal");
    }
}
