package steam.pages;

import framework.Browser;
import framework.baseElement.BaseElement;
import framework.baseElement.Button;
import framework.baseElement.Label;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Log4j2
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
        log.info("Choose category of game offers");
        Label lblGenreOffersCategory = new Label(By.xpath(String.format(commonLocatorForGenreOffersCategory, Header.languageReader.getProperty(genreSubItem))), "Category of offers label");
        lblGenreOffersCategory.mouseMoveToElement();
    }

    public void chooseGameWithHighestDiscount() {
        log.info("Choose random game with the highest discount");
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
        while (!elementList.get(randomIndex).isDisplayed()) {
            btnMoveRight.click();
        }
        Actions actions = new Actions(Browser.driver);
        actions.moveToElement(elementList.get(randomIndex)).perform();
        randomGameWithTheHighestDiscount = gameTitle.getText();
        gameTitle.clickAndWait();
        browser.switchBrowserWindow(1);
    }
}
