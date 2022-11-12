package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResultPage {
    private WebDriver driver;
    private By itemsLinks = By.xpath("//div[@class='product-container']//a[contains(@href,'aliexpress.com/item')]");

    public ResultPage(WebDriver driver) {
        this.driver = driver;
    }
    public ItemDetailsPage clickItemLink(int itemOrder){
        driver.findElements(itemsLinks).get(itemOrder - 1).click();
        driver.getWindowHandles().forEach(tab->driver.switchTo().window(tab));
        return new ItemDetailsPage(driver);
    }
}
