package testCases;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.SettingPage;

import java.io.IOException;

public class SettingPageTest extends BaseTests {

    @Test
    public void settingTest() throws InterruptedException, IOException {
        AccountPage accountPage = homePage.clickOnAccount();
        SettingPage settingPage = accountPage.clickSetting();

        Assert.assertEquals(settingPage.getContentTitle(),settingPage.getPageLinkText());
    }
}