package pages;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class AlertsPage {
    private WebDriver driver;

    public AlertsPage(WebDriver driver) {
        this.driver = driver;
    }
    public boolean isAlertPresent()
    {
        try
        {
            driver.switchTo().alert();
            return true;
        }   // try
        catch (NoAlertPresentException Ex)
        {
            return false;
        }
    }
    public void dismissAlert(){
        driver.switchTo().alert().dismiss();
    }
}
