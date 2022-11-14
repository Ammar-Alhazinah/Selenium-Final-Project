package pages;

import org.openqa.selenium.WebDriver;

public class OrdersPage extends AccountPage{

    private WebDriver driver;
    public OrdersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
