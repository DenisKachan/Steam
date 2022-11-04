package framework.baseElement;

import org.openqa.selenium.By;

public class Select extends BaseElement{

    public Select(By loc) {
        super(loc);
    }

    public void selectValueFromSelect(String value){
        waitElementToBePresent();
        org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(element);
        select.selectByValue(value);
    }
}
