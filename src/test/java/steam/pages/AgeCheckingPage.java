package steam.pages;

import framework.BasePage;
import framework.Browser;
import framework.baseElement.Button;
import framework.baseElement.Dropdown;
import org.openqa.selenium.By;

public class AgeCheckingPage extends BasePage {

    private Browser browser = new Browser();

    private static String pageLocator = "//div[@class='logo']";
    private String commonLocatorForConfirmAgeButton = "//a[@id='view_product_page_btn']/span[contains(text(),'%s')]";
    private final Dropdown slcDay = new Dropdown(By.xpath("//select[@id='ageDay']"),"Day of birth dropdown");
    private final Dropdown slcMonth = new Dropdown(By.xpath("//select[@id='ageMonth']"),"Month of birth dropdown");
    private final Dropdown slcYear = new Dropdown(By.xpath("//select[@id='ageYear']"),"Year of birth dropdown");

    public AgeCheckingPage() {
        super(By.xpath(pageLocator), "AgeCheckingPage");
    }

    public void confirmAgeChecking(){
        browser.switchBrowserWindow(1);
        if (slcDay.elementIsPresent()){
           setBirthDay();
           setBirthMonth();
           setBirthYear();
           clickConfirmButton();}
    }

    public void setBirthDay(){
        slcDay.selectValueFromSelect(HeaderPage.languageReader.getProperty("birthDay"));
    }

    public void setBirthMonth(){
        slcMonth.selectValueFromSelect(HeaderPage.languageReader.getProperty("birthMonth"));
    }

    public void setBirthYear(){
        slcYear.selectValueFromSelect(HeaderPage.languageReader.getProperty("birthYear"));
    }

    public void clickConfirmButton(){
        Button btnConfirmAge = new Button(By.xpath(String.format(commonLocatorForConfirmAgeButton,HeaderPage.languageReader.getProperty("btnConfirmAge"))),"Confirm age button");
        btnConfirmAge.clickAndWait();
    }
}
