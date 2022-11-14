package base;

import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.AlertsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.WindowManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractList;
import java.util.Properties;

public class BaseTests {

    private WebDriver driver;
    protected HomePage homePage;
    protected AlertsPage alertPage;
    protected WindowManager windowManager;

    @BeforeClass
    public void setUp() throws InterruptedException, IOException{
        FileReader fileReader = new FileReader("resources/Files/properties");
        Properties properties = new Properties();
        properties.load(fileReader);
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
        LoginPage loginPage = homePage.hoverElement();
        loginPage.enterEmail(properties.getProperty("email"));
        loginPage.enterPassword(properties.getProperty("password"));
        Thread.sleep(3000);
        loginPage.singIn();

        Thread.sleep(5000);
    }


    @BeforeMethod
    public void clearSearch() {
        homePage.clearSearch();
    }


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

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}