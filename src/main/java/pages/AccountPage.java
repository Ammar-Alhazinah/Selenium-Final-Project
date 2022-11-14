package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {
    private WebDriver driver;


    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public OrdersPage clickOrders() throws InterruptedException {
        clickOnTab(2);
        return new OrdersPage(driver);
    }

    public FeedbackPage clickFeedback() throws InterruptedException {
        clickOnTab(5);
        return new FeedbackPage(driver);
    }

    public SettingPage clickSetting() throws InterruptedException {
        clickOnTab(6);
        return new SettingPage(driver);
    }

    public String getContentTitle() {
        return driver.getTitle();
    }
    public String getPageLinkText(){
        return driver.findElements(By.className("comet-breadcrumb-link")).get(2).getText();
    }
    private void clickOnTab(int tabOrder) throws InterruptedException {
        Thread.sleep(4000);
        driver.findElements(By.className("page-menu-item")).get(tabOrder).click();
    }
}
