package pages;

import org.apache.log4j.FileAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class ResultPage {
    private WebDriver driver;
    private Logger logger = LogManager.getLogger(ResultPage.class);

    private final By itemsLinks = By.xpath("//a[contains(@href,'aliexpress.com/item')]");

    public ResultPage(WebDriver driver) throws IOException {

        SimpleLayout layout = new SimpleLayout();
        FileAppender appender = new FileAppender(layout, "SeleniumLog.log", true);
        logger.addAppender(appender);

        this.driver = driver;
    }

    public ItemDetailsPage clickItemLink(int itemOrder) throws IOException {
        try {
            driver.findElements(itemsLinks).get(itemOrder - 1).click();
            driver.getWindowHandles().forEach(tab -> driver.switchTo().window(tab));
            logger.info("clickItemLink - Passed");
            return new ItemDetailsPage(driver);
        } catch (Exception e) {
            System.out.println(e.getMessage() + "|" + e);
            logger.error("clickItemLink - Failed", e);
            return new ItemDetailsPage(driver);
        }
    }
}
