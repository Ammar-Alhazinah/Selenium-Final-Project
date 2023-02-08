package testCases;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.FeedbackPage;

import java.io.IOException;

public class FeedbackTest extends BaseTests {

    @Test
    public void feedbackTest() throws InterruptedException, IOException {
        AccountPage accountPage = homePage.clickOnAccount();
        FeedbackPage feedbackPage = accountPage.clickFeedback();

        Assert.assertEquals(feedbackPage.getContentTitle(),feedbackPage.getPageLinkText());
    }
}
