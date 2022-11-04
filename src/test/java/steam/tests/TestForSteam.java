package steam.tests;

import framework.BaseTest;
import org.testng.annotations.Test;
import steam.pages.*;

public class TestForSteam extends BaseTest {

    @Override
    @Test
    public void runTest() {
        BaseSteamPage baseSteamPage = new BaseSteamPage();
        baseSteamPage.chooseLanguage("English");
        baseSteamPage.navigateMenu("menuItem","subMenuItem");

        GenreActionPage genreActionPage = new GenreActionPage();
        genreActionPage.chooseGenreOffersCategory("genreSubItem");
        genreActionPage.chooseGameWithHighestDiscount();

        AgeCheckingPage ageCheckingPage = new AgeCheckingPage();
        ageCheckingPage.confirmAgeChecking();

        GamePage gamePage = new GamePage();
        gamePage.checkingGameTitle();

        baseSteamPage.clickInstallSteam();

        DownloadSteamPage downloadSteamPage = new DownloadSteamPage();
        downloadSteamPage.downloadSteam();
        downloadSteamPage.waitSteamToBeDownloaded();
    }
}
