package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FeedbackPage extends AccountPage{
    private WebDriver driver;

    public FeedbackPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Override
    public String getContentTitle(){
        return driver.findElement(By.className("page-title")).getText();

    }
}
