package testCases;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.OrdersPage;

public class OrdersPageTest extends BaseTests {

    @Test
    public void ordersTest() throws InterruptedException {
        AccountPage accountPage = homePage.clickOnAccount();
        OrdersPage orderPage = accountPage.clickOrders();

        Assert.assertEquals(orderPage.getContentTitle(),orderPage.getPageLinkText());
    }
}