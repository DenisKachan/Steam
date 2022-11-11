package steam.tests;

import framework.BaseTest;
import org.testng.annotations.Test;
import steam.pages.*;

public class TestForSteam extends BaseTest {

    @Test
    public void gameChoosingAndSteamInstallingTest() {
        navigateToURL("baseUrl");

        HeaderPage headerPage = new HeaderPage();
        headerPage.chooseLanguage("English");

        BaseSteamPage baseSteamPage = new BaseSteamPage();
        baseSteamPage.navigateMenu("menuItem","subMenuItem");

        GenreActionPage genreActionPage = new GenreActionPage();
        genreActionPage.chooseGenreOffersCategory("genreSubItem");
        genreActionPage.chooseGameWithHighestDiscount();

        AgeCheckingPage ageCheckingPage = new AgeCheckingPage();
        ageCheckingPage.confirmAgeChecking();

        GamePage gamePage = new GamePage();
        gamePage.checkingGameTitle();

        headerPage.clickInstallSteam();

        DownloadSteamPage downloadSteamPage = new DownloadSteamPage();
        downloadSteamPage.downloadSteam();
        downloadSteamPage.waitSteamToBeDownloaded();
    }
}
