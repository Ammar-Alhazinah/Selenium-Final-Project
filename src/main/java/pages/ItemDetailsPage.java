package pages;

import org.apache.log4j.FileAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class ItemDetailsPage {
    private WebDriver driver;
    private Logger logger = LogManager.getLogger(ItemDetailsPage.class);
    private final By addToCartBtn = By.xpath("//button[contains(@class,'add')]");
    private final By itemTitle = By.xpath("//h1[@class = 'product-title-text']");

    public ItemDetailsPage(WebDriver driver) throws IOException {


        SimpleLayout layout = new SimpleLayout();
        FileAppender appender = new FileAppender(layout, "SeleniumLog.log", true);
        logger.addAppender(appender);
        this.driver = driver;
    }

    public String getItemTitle() {
        String result = null;
        try {
            result = driver.findElement(itemTitle).getText();
            logger.info("getItemTitle - Passed");
        } catch (Exception e) {
            logger.error("getItemTitle - Failed", e);
        }
        return result;
    }

    public String getItemLink() {
        String itemLink = null;
        try {
            itemLink = driver.getCurrentUrl();
            logger.info("getItemLink - Passed");
        } catch (Exception e) {
            logger.error("getItemLink - Failed", e);
        }
        return itemLink;
    }

    public AddCartResult addToCart() throws IOException {
        try {
            driver.findElement(addToCartBtn).click();
            logger.info("addToCart - Passed");

        } catch (Exception e) {
            logger.error("addToCart - Failed", e);
        }
        return new AddCartResult(driver);
    }
}
