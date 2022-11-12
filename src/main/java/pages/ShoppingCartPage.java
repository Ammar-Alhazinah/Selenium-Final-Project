package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPage {
    WebDriver driver;

    private By totalItems = By.className("cart-product-wrap");
    private By totalPaymentTxt = By.xpath("//div[@class='cart-summary-item-content']/span");
    private By selectAllItemsBtn = By.xpath("//div[@class='cart-header-checkbox']/label/input");
    private By deleteItemsBtn = By.xpath("//div[@class='cart-header-checkbox']/button");
    private By cartEmpty = By.className("cart-empty");

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
    }

    public int numberOfItems(){
        return driver.findElements(totalItems).size();
    }

    public void deleteAllElements(){
        driver.findElement(selectAllItemsBtn).click();
        driver.findElement(deleteItemsBtn).click();
    }


    public boolean checkCartEmpty(){
        try {
            driver.findElements(cartEmpty);
            return true;
        }catch (Exception exception){
            return false;
        }
    }

}
