package testCases;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTests {
    @Test(priority = 1)
    public void loginTest() throws InterruptedException {
        Thread.sleep(2000);
        String welcomeTxt = homePage.getLoggedUser();
        System.out.println(welcomeTxt);
        Assert.assertEquals(welcomeTxt, "Welcome to AliExpress!");
    }
}
