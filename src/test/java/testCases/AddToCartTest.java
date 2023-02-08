package testCases;

import DataProvider.DataProviderClass;
import HelperClasses.Constants;
import HelperClasses.WriteCsvFile;
import base.BaseTests;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import java.util.List;

import static HelperClasses.ReadCsvFile.getNumberOfItems;


public class AddToCartTest extends BaseTests {
    static List<String[]> csvLines = new ArrayList<String[]>();

    @Test(dataProviderClass = DataProviderClass.class, dataProvider = "Data")
    public void addToCartTest(ITestContext context, String itemName) throws IOException {
        homePage.enterText(itemName);

        ResultPage resultPage = homePage.hitSearch();

        ItemDetailsPage itemDetailsPage = resultPage.clickItemLink(1);
        String[] lineDetails = {itemName, itemDetailsPage.getItemTitle(), itemDetailsPage.getItemLink()};
        csvLines.add(lineDetails);

        int index = context.getAllTestMethods().length - 1;
        String methodName = context.getAllTestMethods()[index].getMethodName();

        getDriver().manage().timeouts().getPageLoadTimeout();

        if (methodName.equals("addToCartTest")) {
            AddCartResult addCartResult = itemDetailsPage.addToCart();
            ShoppingCartPage shoppingCartPage = addCartResult.clickViewShoppingCart();

            Assert.assertEquals(shoppingCartPage.numberOfItems(), getNumberOfItems() - 1, "Number of items in cart is not as expected");

        } else {
            AddCartResult addCartResult = itemDetailsPage.addToCart();

            addCartResult.clickContinueShopping();
        }

        homePage.closeTab();
    }

    @Test
    public void checkNumberOfCartItemsTest() throws IOException {
        ShoppingCartPage shoppingCartPage = homePage.clickOnShopCart();

        Assert.assertEquals(shoppingCartPage.numberOfItems(), getNumberOfItems() - 1, "Number of items in cart is not as expected");

    }

    @AfterClass
    public static void afterClass() {
        String[] headers = {"Item Name", "Item Title", "Item Link"};
        WriteCsvFile.writeDataLineByLine(Constants.OUTPUT_FILE, csvLines, headers);
        try {
            File outputFile = new File(Constants.OUTPUT_FILE);
            Allure.addAttachment("output.csv", FileUtils.openInputStream(outputFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
