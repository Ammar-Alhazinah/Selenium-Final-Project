package pages;

import org.apache.log4j.FileAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.io.IOException;


public class LoginPage {

    private WebDriver driver;

    private Logger logger = LogManager.getLogger(LoginPage.class);

    private final By emailField = By.id("fm-login-id");

    private final By passwordField = By.id("fm-login-password");


    public LoginPage(WebDriver driver) throws IOException {

        SimpleLayout layout = new SimpleLayout();
        FileAppender appender = new FileAppender(layout, "SeleniumLog.log", true);
        logger.addAppender(appender);this.driver = driver;
    }

    public void enterEmail(String email) {
        try {
            driver.findElement(emailField).sendKeys(email);
            logger.info("EnterEmail - Passed");
        } catch (Exception e) {
            logger.error("EnterEmail - Failed", e);
        }
    }

    public void enterPassword(String password) {
        try {
            driver.findElement(passwordField).sendKeys(password);
            logger.info("EnterPassword - Passed");

        } catch (Exception e) {
            logger.error("EnterPassword - Failed", e);

        }
    }

    public void singIn(String email, String password) {
        try {
            enterEmail(email);
            enterPassword(password);
            driver.findElement(passwordField).sendKeys(Keys.RETURN);
            logger.info("singIn - Passed");

        } catch (Exception e) {
            logger.error("singIn - Failed", e);

        }
    }
}
