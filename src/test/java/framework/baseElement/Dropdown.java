package framework.baseElement;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class Dropdown extends BaseElement{

    public Dropdown(By loc, String name) {
        super(loc,name);
    }

    public void selectValueFromSelect(String value){
        elementIsPresent();
        Select select = new Select(element);
        select.selectByValue(value);
    }
}
