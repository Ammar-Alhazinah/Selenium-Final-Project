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


public class ShoppingCartPage {
    WebDriver driver;
    private Logger logger = LogManager.getLogger(ShoppingCartPage.class);

    private final By totalItems = By.className("cart-product-wrap");

    public ShoppingCartPage(WebDriver driver) throws IOException {
        SimpleLayout layout = new SimpleLayout();
        FileAppender appender = new FileAppender(layout, "SeleniumLog.log", true);
        logger.addAppender(appender);
        this.driver = driver;
    }

    public int numberOfItems() {
        return driver.findElements(totalItems).size();
    }


}
