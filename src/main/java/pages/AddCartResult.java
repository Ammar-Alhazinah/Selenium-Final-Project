package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddCartResult {
    WebDriver driver;
    private By addCartResultTxt = By.className("addcart-result-title");
    private By viewShoppingCartBtn = By.xpath("//div[@class='addcart-result-action']/a/button");
    private By continueShoppingBtn = By.xpath("//div[@class='addcart-result-action']//button");
    private By closeDialogBtn = By.className("next-dialog-close");

    public AddCartResult(WebDriver driver) {
        this.driver = driver;
    }

    public void continueShoppingBtnClick(){

    }
}

