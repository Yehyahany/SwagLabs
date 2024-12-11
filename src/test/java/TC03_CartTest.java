import Pages.P01_LoginPage;
import Pages.P02_LandingPage;
import Pages.P03_CartPage;
import Utilities.DataUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class TC03_CartTest {

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
    public void ComparingPricesTC() throws IOException {
        String price = new P01_LoginPage(driver)
                .enterUsername(DataUtils.getJsonData("validLogin", "username"))
                .enterPassword(DataUtils.getJsonData("validLogin", "password"))
                .clickOnLoginButton()
                .addRandomProducts(2, 6)
                .getTotalPriceOfSelectedProducts();
        new P02_LandingPage(driver).clickOnCartIcon();
        Assert.assertTrue(new P03_CartPage(driver).comparinPrices(price));
    }

    @AfterMethod
    public void quit() {
        driver.quit();
    }
}
