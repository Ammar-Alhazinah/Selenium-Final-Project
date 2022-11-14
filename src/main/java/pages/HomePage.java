package pages;

import com.google.common.io.Files;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;

public class HomePage {

    private WebDriver driver;
    private By searchField = By.id("search-key");

    private By userAccount = By.className("user-account-port");

    private By singInBtn = By.className("sign-btn");

    private By shopCartIcon = By.xpath("//div[contains(@class,'shopcart')]/a[contains(@href,'cart')]");

    private By welcomeToAliExpressTxt = By.className("welcome-title");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage hoverElement() {

        //Creating object of an Actions class
        Actions action = new Actions(driver);

        WebElement element = driver.findElement(userAccount);
        //Performing the mouse hover action on the target element.
        action.moveToElement(element);

        WebElement singInElement = driver.findElement(singInBtn);
        action.moveToElement(singInElement);
        action.click().build().perform();

        return new LoginPage(driver);
    }

    public void enterText(String text) {
        driver.findElement(searchField).sendKeys(text);
    }

    public ResultPage hitSearch() {
        driver.findElement(searchField).sendKeys(Keys.RETURN);
        return new ResultPage(driver);
    }

    public ShoppingCartPage clickOnShopCart() {
        driver.findElement(shopCartIcon).click();
        return new ShoppingCartPage(driver);
    }

    public AccountPage clickOnAccount(){
        driver.findElement(userAccount).click();
        return new AccountPage(driver);
    }

    public void clearSearch() {
        driver.findElement(searchField).clear();
    }

    public void closeTab() {
        driver.close();
        driver.getWindowHandles().forEach(tab -> driver.switchTo().window(tab));
    }

    public void takeScreenShot(){
        var camera = (TakesScreenshot)driver;
        File screenshot = camera.getScreenshotAs(OutputType.FILE);
        try{
            File screenshotFile = new File("resources/screenshots/items_in_cart.png");
            Files.move(screenshot, screenshotFile);
            Allure.addAttachment("items_in_cart.png", FileUtils.openInputStream(screenshotFile));
        }catch(IOException e){
            e.printStackTrace();
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

            return "Welcome to AliExpress!";
        } catch (Exception ex) {
            return "You are not logged in";
        }

    }

}