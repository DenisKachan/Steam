package steam.pages;

import framework.baseElement.Button;
import framework.baseElement.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GenreActionPage extends BaseSteamPage {

    private static String formLocator = "//div[contains(@class,'contenthubmain')]";
    private final String commonLocatorForGenreOffersCategory = "//div[contains(@class,'partnereventshared_SaleSectionHeader')][contains(text(),'%s')]";
    private final Label gameTitle = new Label(By.xpath("//div[contains(@class,'gamehover_Bottom')]/descendant::div[contains(@class,'gamehover_GameTitle')]"));
    private final Label lblDiscounts = new Label(By.xpath("//div[@id='SaleSection_61186']/descendant::div[contains(@class,'salepreviewwidgets_StoreSaleDiscountBox')]"));
    private final Button btnMoveRight = new Button(By.xpath("//div[@id='SaleSection_61186']/descendant::button[contains(@class,'right')]"));
    public static String randomGameWithTheHighestDiscount;

    public GenreActionPage() {
        super(By.xpath(formLocator));
    }

    public void chooseGenreOffersCategory(String genreSubItem){
        Label lblGenreOffersCategory = new Label(By.xpath(String.format(commonLocatorForGenreOffersCategory, languageReader.getProperty(genreSubItem))));
        lblGenreOffersCategory.mouseMoveToElement();
    }

    public void chooseGameWithHighestDiscount() {
        ArrayList<String> tempList = new ArrayList<>();
        List<WebElement> elementList= lblDiscounts.getListOfElements();
        for(WebElement optionElement : elementList)
        {tempList.add(optionElement.getAttribute("innerText"));}
        Collections.sort(tempList);
        String maxDiscount = tempList.get(tempList.size()-1);
        List<Integer> indexesOfMaxDiscounts = new ArrayList<>();
        for (int i = 0; i<tempList.size();i++){
            if (maxDiscount.equals(tempList.get(i)))
            {indexesOfMaxDiscounts.add(i);}}
        Random random = new Random();
        int randomIndex = indexesOfMaxDiscounts.get(random.nextInt(indexesOfMaxDiscounts.size()));
        while (!elementList.get(randomIndex).isDisplayed()){
        btnMoveRight.click();}
        Actions actions = new Actions(browser.getDriver());
        actions.moveToElement(elementList.get(randomIndex)).perform();
        randomGameWithTheHighestDiscount = gameTitle.getText();
        gameTitle.clickAndWait();
    }
}
