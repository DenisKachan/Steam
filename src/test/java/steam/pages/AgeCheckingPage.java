package steam.pages;

import framework.baseElement.Button;
import framework.baseElement.Select;
import org.openqa.selenium.By;

public class AgeCheckingPage extends BaseSteamPage{

    private final String commonLocatorForConfirmAgeButton = "//a[@id='view_product_page_btn']/span[contains(text(),'%s')]";
    private final Select slcDay = new Select(By.xpath("//select[@id='ageDay']"));
    private final Select slcMonth = new Select(By.xpath("//select[@id='ageMonth']"));
    private final Select slcYear = new Select(By.xpath("//select[@id='ageYear']"));


    public void confirmAgeChecking(){
        Button btnConfirmAge = new Button(By.xpath(String.format(commonLocatorForConfirmAgeButton,languageReader.getProperty("btnConfirmAge"))));
        browser.switchToTheNextBrowserWindow();
        if (btnConfirmAge.isPresent()){
            slcDay.selectValueFromSelect(languageReader.getProperty("birthDay"));
            slcMonth.selectValueFromSelect(languageReader.getProperty("birthMonth"));
            slcYear.selectValueFromSelect(languageReader.getProperty("birthYear"));
            btnConfirmAge.clickAndWait();}
    }
}
