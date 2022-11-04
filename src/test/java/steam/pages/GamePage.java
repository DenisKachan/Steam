package steam.pages;

import framework.baseElement.Label;
import org.openqa.selenium.By;
import org.testng.Assert;

public class GamePage extends BaseSteamPage{

    private static String formLocator = "//div[@class='page_content_ctn']";
    private final Label lblGameTitle = new Label(By.xpath("//div[@id='appHubAppName']"));

    public GamePage() {
        super(By.xpath(formLocator));
    }

    public void checkingGameTitle(){
        String gameTitle = lblGameTitle.getText();
        Assert.assertEquals(gameTitle, GenreActionPage.randomGameWithTheHighestDiscount);
    }
}
