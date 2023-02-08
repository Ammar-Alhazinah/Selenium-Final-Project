package pages;

import com.google.common.io.Files;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.FileAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;

public class HomePage {

    private WebDriver driver;
    private Logger logger = LogManager.getLogger(HomePage.class);
    private final By searchField = By.id("search-key");

    private final By userAccount = By.className("user-account-port");

    private final By singInBtn = By.className("sign-btn");

    private final By shopCartIcon = By.xpath("//div[contains(@class,'shopcart')]/a[contains(@href,'cart')]");

    private final By welcomeToAliExpressTxt = By.className("welcome-title");


    public HomePage(WebDriver driver) throws IOException {
        SimpleLayout layout = new SimpleLayout();
        FileAppender appender = new FileAppender(layout, "SeleniumLog.log", true);
        logger.addAppender(appender);

        this.driver = driver;
    }

    public LoginPage hoverElement() throws IOException {
        try {
            //Creating object of an Actions class
            Actions action = new Actions(driver);

            WebElement element = driver.findElement(userAccount);
            //Performing the mouse hover action on the target element.
            action.moveToElement(element);

            WebElement singInElement = driver.findElement(singInBtn);
            action.moveToElement(singInElement);
            action.click().build().perform();
            logger.info("hoverElement - Passed");
        } catch (Exception e) {

            logger.error("hoverElement - Failed", e);
        }


        return new LoginPage(driver);
    }

    public void enterText(String text) {
        try {

            driver.findElement(searchField).sendKeys(text);
            logger.info("enterText - Passed");
        } catch (Exception e) {

            logger.error("enterText - Failed", e);
        }
    }

    public ResultPage hitSearch() throws IOException {
        try {

            driver.findElement(searchField).sendKeys(Keys.RETURN);
            logger.info("hitSearch - Passed");
        } catch (Exception e) {

            logger.error("hitSearch - Failed", e);
        }
        return new ResultPage(driver);
    }

    public ShoppingCartPage clickOnShopCart() throws IOException {
        try {
            driver.findElement(shopCartIcon).click();
            logger.info("clickOnShopCart - Passed");
        } catch (Exception e) {

            logger.error("clickOnShopCart - Failed", e);
        }
        return new ShoppingCartPage(driver);
    }

    public AccountPage clickOnAccount() throws IOException {
        try {

            driver.findElement(userAccount).click();
            logger.info("clickOnAccount - Passed");
        } catch (Exception e) {

            logger.error("clickOnAccount - Failed", e);
        }
        return new AccountPage(driver);
    }

    public void clearSearch() {
        try {

            driver.findElement(searchField).clear();
            logger.info("clearSearch - Passed");
        } catch (Exception e) {

            logger.error("clearSearch - Failed", e);
        }
    }

    public void closeTab() {
        try {

            driver.close();
            driver.getWindowHandles().forEach(tab -> driver.switchTo().window(tab));
            logger.info("closeTab - Passed");
        } catch (Exception e) {

            logger.error("closeTab - Failed", e);
        }
    }


    public String getLoggedUser() {
        //Creating object of an Actions class
        Actions action = new Actions(driver);

        WebElement element = driver.findElement(userAccount);
        //Performing the mouse hover action on the target element.
        action.moveToElement(element);

        try {
            driver.findElement(welcomeToAliExpressTxt);
            logger.info("getLoggedUser - Passed");
            return "Welcome to AliExpress!";
        } catch (Exception ex) {
            logger.error("getLoggedUser - Failed", ex);
            return "You are not logged in";
        }

    }

}