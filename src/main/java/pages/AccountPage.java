package pages;

import org.apache.log4j.FileAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class AccountPage {
    private WebDriver driver;
    private Logger logger = LogManager.getLogger(AccountPage.class);


    public AccountPage(WebDriver driver) throws IOException {


        SimpleLayout layout = new SimpleLayout();
        FileAppender appender = new FileAppender(layout, "SeleniumLog.log", true);
        logger.addAppender(appender);
        this.driver = driver;
    }

    public OrdersPage clickOrders() throws IOException {
        try {
            clickOnTab(2);
            logger.info("clickOrders - Passed");
        } catch (Exception e) {
            logger.error("clickOrders - Failed", e);
        }
        return new OrdersPage(driver);
    }

    public FeedbackPage clickFeedback() throws IOException {
        try {
            clickOnTab(5);

            logger.info("clickFeedback - Passed");
        } catch (Exception e) {
            logger.error("clickFeedback - Failed", e);
        }
        return new FeedbackPage(driver);
    }

    public SettingPage clickSetting() throws IOException {
        try {
            logger.info("clickSetting - Passed");
            clickOnTab(6);
        } catch (Exception e) {
            logger.error("clickSetting - Failed", e);
        }
        return new SettingPage(driver);
    }

    public String getContentTitle() {
        return driver.getTitle();
    }

    public String getPageLinkText() {
        return driver.findElements(By.className("comet-breadcrumb-link")).get(2).getText();
    }

    private void clickOnTab(int tabOrder) {
        driver.findElements(By.className("page-menu-item")).get(tabOrder).click();
    }
}
