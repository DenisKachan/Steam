package steam.tests;

import framework.BaseTest;
import org.testng.annotations.Test;
import steam.pages.*;

public class SteamTest extends BaseTest {

    @Test
    public void gameChoosingAndSteamInstallingTest() {
        Header header = new Header();
        header.chooseLanguage("English");

        MainPage mainPage = new MainPage();
        mainPage.navigateMenu("menuItem", "subMenuItem");

        GenreActionPage genreActionPage = new GenreActionPage();
        genreActionPage.chooseCategoryOfOffers("genreSubItem");
        genreActionPage.chooseGameWithHighestDiscount();

        if (browser.getDriver().getCurrentUrl().contains("/agecheck/")) {
            AgeCheckingPage ageCheckingPage =new AgeCheckingPage();
            ageCheckingPage.setBirthDay();
            ageCheckingPage.setBirthMonth();
            ageCheckingPage.setBirthYear();
            ageCheckingPage.clickConfirmButton();
        }

        GamePage gamePage = new GamePage();
        gamePage.checkingGameTitle();

        header.clickInstallSteam();

        DownloadSteamPage downloadSteamPage = new DownloadSteamPage();
        downloadSteamPage.downloadSteam();
        downloadSteamPage.waitSteamToBeDownloaded();
    }
}
