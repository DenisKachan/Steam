package steam.pages;

import framework.baseElement.Button;
import framework.baseElement.Dropdown;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

@Log4j2
public class AgeCheckingPage extends BaseSteamPage {

    private static String pageLocator = "//select[@id='ageDay']";
    private String commonLocatorForConfirmAgeButton = "//a[@id='view_product_page_btn']/span[contains(text(),'%s')]";
    private Dropdown slcDay = new Dropdown(By.xpath("//select[@id='ageDay']"), "Day of birth dropdown");
    private Dropdown slcMonth = new Dropdown(By.xpath("//select[@id='ageMonth']"), "Month of birth dropdown");
    private Dropdown slcYear = new Dropdown(By.xpath("//select[@id='ageYear']"), "Year of birth dropdown");

    public AgeCheckingPage() {
        super(By.xpath(pageLocator), "AgeCheckingPage");
    }

    public void setBirthDay() {
        log.info("Set the day of birth");
        slcDay.selectValueFromDropdown(Header.languageReader.getProperty("birthDay"));
    }

    public void setBirthMonth() {
        log.info("Set the month of birth");
        slcMonth.selectValueFromDropdown(Header.languageReader.getProperty("birthMonth"));
    }

    public void setBirthYear() {
        log.info("Set the year of birth");
        slcYear.selectValueFromDropdown(Header.languageReader.getProperty("birthYear"));
    }

    public void clickConfirmButton() {
        log.info("Confirm age");
        Button btnConfirmAge = new Button(By.xpath(String.format(commonLocatorForConfirmAgeButton, Header.languageReader.getProperty("btnConfirmAge"))), "Confirm age button");
        btnConfirmAge.clickAndWait();
    }
}
