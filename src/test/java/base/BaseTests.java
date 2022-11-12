package base;

import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.AlertsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.WindowManager;

import java.io.File;
import java.io.IOException;
import java.util.AbstractList;

public class BaseTests {

    private WebDriver driver;
    protected HomePage homePage;
    protected AlertsPage alertPage;
    protected WindowManager windowManager;

    @BeforeClass
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
//        options.addArguments("--block-new-web-contents");
        driver = new ChromeDriver(options);

        windowManager = new WindowManager(driver);
        windowManager.maximize();
        driver.get("https://www.aliexpress.com/all-wholesale-products.html");
        driver.manage().timeouts().getPageLoadTimeout();
        homePage = new HomePage(driver);
//        Thread.sleep(2000);
        LoginPage loginPage = homePage.hoverElement();
//        Thread.sleep(5000);
        loginPage.enterEmail("acount.test.01.01@gmail.com");
        loginPage.enterPassword("trewqst@123");
        Thread.sleep(3000);
        loginPage.singIn();

        Thread.sleep(5000);
    }

  /*  public void goHome() {
        driver.get("https://www.aliexpress.com/all-wholesale-products.html");

        homePage = new HomePage(driver);
        alertPage = new AlertsPage(driver);

    }*/

    @BeforeMethod
    public void clearSearch() {
        homePage.clearSearch();
    }

    /*@AfterMethod
    public void closeTab() {
        driver.close();
        driver.getWindowHandles().forEach(tab -> driver.switchTo().window(tab));
    }*/


    @AfterMethod
    public void recordFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            var camera = (TakesScreenshot) driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try {
                Files.move(screenshot, new File("resources/screenshots/" + result.getName() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}