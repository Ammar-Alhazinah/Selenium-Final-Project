package testCases;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTests {
    @Test
    public void loginTest() throws InterruptedException {
        Thread.sleep(2000);
        String welcomeTxt = homePage.getLoggedUser();
        Assert.assertEquals(welcomeTxt, "Welcome to AliExpress!");
    }
}
