package pages;

import org.apache.log4j.FileAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class AddCartResult {
    WebDriver driver;
    private Logger logger = LogManager.getLogger(AddCartResult.class);
    private final By dialogDiv = By.xpath("//div[@role='dialog']");
    private final By viewShoppingCartBtn = By.xpath("//button[contains(@class,'view-shopcart')]");
    private final By continueShoppingBtn = By.xpath("//button[contains(@class,'continue-shop')]");
    WebElement dialog;

    public AddCartResult(WebDriver driver) throws IOException {
        SimpleLayout layout = new SimpleLayout();
        FileAppender appender = new FileAppender(layout, "SeleniumLog.log", true);
        logger.addAppender(appender);
        this.driver = driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        dialog = wait.until(ExpectedConditions.presenceOfElementLocated(dialogDiv));
    }

    public void clickContinueShopping() {
        try {

            WebElement continueShoppingButton = dialog.findElement(continueShoppingBtn);
            continueShoppingButton.click();
            logger.info("clickContinueShopping - Passed");
        } catch (Exception e) {
            logger.error("clickContinueShopping - Failed", e);
        }
    }

    public ShoppingCartPage clickViewShoppingCart() throws IOException {
        try {

            WebElement viewShoppingCartButton = dialog.findElement(viewShoppingCartBtn);
            viewShoppingCartButton.click();
            logger.info("clickViewShoppingCart - Passed");
        } catch (Exception e) {

            logger.error("clickViewShoppingCart - Failed", e);
        }
        return new ShoppingCartPage(driver);
    }
}

