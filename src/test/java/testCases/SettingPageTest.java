package testCases;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.SettingPage;

public class SettingPageTest extends BaseTests {

    @Test
    public void settingTest() throws InterruptedException {
        AccountPage accountPage = homePage.clickOnAccount();
        SettingPage settingPage = accountPage.clickSetting();

        Assert.assertEquals(settingPage.getContentTitle(),settingPage.getPageLinkText());
    }
}