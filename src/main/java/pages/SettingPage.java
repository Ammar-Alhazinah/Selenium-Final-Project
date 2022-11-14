package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SettingPage extends AccountPage{
    private WebDriver driver;

    public SettingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    @Override
    public String getContentTitle(){
        return driver.findElement(By.xpath("//p[contains(@class,'-title-text')]")).getText();

    }
}
