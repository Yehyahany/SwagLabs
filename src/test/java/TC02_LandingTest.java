import Pages.P01_LoginPage;
import Pages.P02_LandingPage;
import Utilities.DataUtils;
import Utilities.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

public class TC02_LandingTest {
    private WebDriver driver;


    @BeforeMethod
    public void setup() throws IOException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(DataUtils.getPropertyValue("Environments", "LoginURL"));
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void ComparingTC() throws IOException {
        new P01_LoginPage(driver)
                .enterUsername(DataUtils.getJsonData("validLogin", "username"))
                .enterPassword(DataUtils.getJsonData("validLogin", "password"))
                .clickOnLoginButton()
                .addAllProductsToCart();
        Assert.assertTrue(new P02_LandingPage(driver).compare());
    }

    @Test
    public void addingRandomNumberOfProductsTC() throws FileNotFoundException {
        new P01_LoginPage(driver)
                .enterUsername(DataUtils.getJsonData("validLogin", "username"))
                .enterPassword(DataUtils.getJsonData("validLogin", "password"))
                .clickOnLoginButton()
                .addRandomProducts(3, 6);
        Assert.assertTrue(new P02_LandingPage(driver).compare());
    }

    @Test
    public void clickOnCartTC() throws IOException {
        new P01_LoginPage(driver)
                .enterUsername(DataUtils.getJsonData("validLogin", "username"))
                .enterPassword(DataUtils.getJsonData("validLogin", "password"))
                .clickOnLoginButton()
                .clickOnCartIcon();
        Assert.assertTrue(Utility.verifyURL(driver, DataUtils.getPropertyValue("Environments", "CartURL")));
    }

    @AfterMethod
    public void quit() {
        driver.quit();
    }
}
