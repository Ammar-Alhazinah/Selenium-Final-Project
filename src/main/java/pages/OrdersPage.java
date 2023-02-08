package pages;

import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class OrdersPage extends AccountPage{

    private WebDriver driver;
    public OrdersPage(WebDriver driver) throws IOException {
        super(driver);
        this.driver = driver;
    }
}
