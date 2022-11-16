package steam.pages;

import framework.Browser;
import framework.baseElement.BaseElements;
import framework.baseElement.Button;
import framework.baseElement.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GenreActionPage extends BaseSteamPage {

    private Browser browser = new Browser();
    private static String pageLocator = "//div[contains(@class,'contenthubmain')]";
    private String commonLocatorForGenreOffersCategory = "//div[contains(@class,'partnereventshared_SaleSectionHeader')][contains(text(),'%s')]";
    private Label gameTitle = new Label(By.xpath("//div[contains(@class,'gamehover_Bottom')]/descendant::div[contains(@class,'gamehover_GameTitle')]"), "Title of the game");
    private Label lblDiscounts = new Label(By.xpath("//div[@id='SaleSection_61186']/descendant::div[contains(@class,'salepreviewwidgets_StoreSaleDiscountBox')]"), "Discount label");
    private Button btnMoveRight = new Button(By.xpath("//div[@id='SaleSection_61186']/descendant::button[contains(@class,'right')]"), "Move right button");
    public static String randomGameWithTheHighestDiscount;

    public GenreActionPage() {
        super(By.xpath(pageLocator), "GenreActionPage");
    }

    public void chooseCategoryOfOffers(String genreSubItem) {
        Label lblGenreOffersCategory = new Label(By.xpath(String.format(commonLocatorForGenreOffersCategory, Header.languageReader.getProperty(genreSubItem))), "Category of offers label");
        lblGenreOffersCategory.mouseMoveToElement();
    }

    public void chooseGameWithHighestDiscount() {
        ArrayList<String> tempList = new ArrayList<>();
        List<WebElement> elementList = lblDiscounts.getListOfElements();
        for (WebElement optionElement : elementList) {
            tempList.add(optionElement.getAttribute("innerText"));
        }
        Collections.sort(tempList);
        String maxDiscount = tempList.get(tempList.size() - 1);
        List<Integer> indexesOfMaxDiscounts = new ArrayList<>();
        for (int i = 0; i < tempList.size(); i++) {
            if (maxDiscount.equals(tempList.get(i))) {
                indexesOfMaxDiscounts.add(i);
            }
        }
        Random random = new Random();
        int randomIndex = indexesOfMaxDiscounts.get(random.nextInt(indexesOfMaxDiscounts.size()));
        BaseElements gamesWithDiscounts = new BaseElements(elementList, "All games with discounts");
        while (!gamesWithDiscounts.elementFromListDisplayed(randomIndex)) {
            btnMoveRight.click();
        }
        gamesWithDiscounts.mouseMoveToTheElementFromList(randomIndex);
        randomGameWithTheHighestDiscount = gameTitle.getText();
        gameTitle.clickAndWait();
        browser.switchBrowserWindow(1);
    }
}
