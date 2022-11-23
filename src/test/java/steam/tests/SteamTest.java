package steam.tests;

import framework.BaseTest;
import org.testng.annotations.Test;
import steam.pageObjects.pages.*;

public class SteamTest extends BaseTest {

    @Test
    public void gameChoosingAndSteamInstallingTest() {
        MainPage mainPage = new MainPage();
        mainPage.getHeader().chooseLanguage("English");
        mainPage.navigateMenu("menuItem", "subMenuItem");

        GenreActionPage genreActionPage = new GenreActionPage();
        genreActionPage.chooseCategoryOfOffers("genreSubItem");
        genreActionPage.chooseGameWithHighestDiscount();

        if (browser.getDriver().getCurrentUrl().contains("/agecheck/")) {
            AgeCheckingPage ageCheckingPage = new AgeCheckingPage();
            ageCheckingPage.setBirthDay();
            ageCheckingPage.setBirthMonth();
            ageCheckingPage.setBirthYear();
            ageCheckingPage.clickConfirmButton();
        }

        GamePage gamePage = new GamePage();
        gamePage.checkingGameTitle();
        gamePage.getHeader().clickInstallSteam();

        DownloadSteamPage downloadSteamPage = new DownloadSteamPage();
        downloadSteamPage.downloadSteam();
        downloadSteamPage.waitSteamToBeDownloaded();
    }
}
