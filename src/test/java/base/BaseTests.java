package base;

import com.google.common.io.Files;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import utils.WindowManager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import HelperClasses.Constants;

public class BaseTests {

    public WebDriver getDriver() {
        return driver;
    }

    private WebDriver driver;
    protected HomePage homePage;
    protected WindowManager windowManager;

    @BeforeClass
    public void setUp() throws IOException {
        FileReader fileReader = new FileReader(Constants.PROPERTIES_FILE);
        Properties properties = new Properties();

        properties.load(fileReader);
        System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_EXE);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);

        windowManager = new WindowManager(driver);
        windowManager.maximize();
        driver.get(Constants.ALIEXPRESS_URL);

        driver.manage().timeouts().getPageLoadTimeout();
        homePage = new HomePage(driver);
            LoginPage loginPage = homePage.hoverElement();
            String email = properties.getProperty("email");
            String password = properties.getProperty("password");
            loginPage.singIn(email, password);

            driver.get(Constants.ALIEXPRESS_URL);

    }


    @BeforeMethod
    public void clearSearch() {
        try {
            driver.manage().timeouts().getPageLoadTimeout();
            homePage.clearSearch();
        } catch (Exception e) {
            driver.get(Constants.ALIEXPRESS_URL);
        }

    }


    @AfterMethod
    public void recordFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            var camera = (TakesScreenshot) driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try {
                File screenshotFile = new File(Constants.SCREENSHOTS_FOLDER + result.getName() + ".png");

                Files.move(screenshot, screenshotFile);

                Allure.addAttachment(result.getName() + ".png", FileUtils.openInputStream(screenshotFile));


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    @AfterTest
//    public void tearDown() {
//        driver.quit();
//    }
}