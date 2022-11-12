package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ItemDetailsPage {
    private WebDriver driver;
    private By addToCartBtn = By.xpath("//span[2][contains(@class, 'addcart')]//button");
    private By itemTitle = By.xpath("//h1[@class = 'product-title-text']");

    public ItemDetailsPage(WebDriver driver) {
        this.driver = driver;
    }
    public String getItemTitle(){
        return driver.findElement(itemTitle).getText();
    }
    public String getItemLink(){
        return driver.getCurrentUrl();
    }
    public void addToCart(){
        driver.findElement(addToCartBtn).click();
    }
}
