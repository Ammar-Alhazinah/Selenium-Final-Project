package testCases;

import DataProvider.DataProviderClass;
import HelpClasses.WriteCsvFile;
import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.*;
import java.util.ArrayList;
import java.util.List;



public class Tests extends BaseTests {
  static List<String[]> csvLines = new ArrayList<String[]>();

@Test(priority = 1)
public void testLogin(){
    String welcomeTxt = homePage.getLoggedUser();
    System.out.println(welcomeTxt);
    Assert.assertEquals(welcomeTxt, "Welcome to AliExpress!");
}
    @Test( priority = 2,dataProviderClass = DataProviderClass.class , dataProvider = "Data")
    public void testAddItemsToCart(String itemName) throws InterruptedException {

        Thread.sleep(2000);
        homePage.enterText(itemName);

        ResultPage resultPage = homePage.hitSearch();
        Thread.sleep(7000);

        ItemDetailsPage itemDetailsPage = resultPage.clickItemLink(1);
        Thread.sleep(10000);

        itemDetailsPage.addToCart();
        Thread.sleep(3000);

        String[] lineDetails = {itemName, itemDetailsPage.getItemTitle(), itemDetailsPage.getItemLink()};
        csvLines.add(lineDetails);

        homePage.closeTab();

    }

    @Test(priority = 3)
    public void testCheckItems(){
        ShoppingCartPage shoppingCartPage = homePage.clickOnShopCart();
        homePage.takeScreenShot();
        Assert.assertEquals(shoppingCartPage.numberOfItems(), 8);
    }


    @AfterClass
    public static void afterClass() {
        String[] headers = {"Item Name","Item Title", "Item Link"};
        WriteCsvFile.writeDataLineByLine("resources/Files/output.csv", csvLines, headers);
    }
}
