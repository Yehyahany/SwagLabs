import Pages.P01_LoginPage;
import Utilities.DataUtils;
import Utilities.Utility;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class TC04_CheckoutTest {
    private final String zipf = new Faker().number().digits(5);
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
    public void CheckOutStepOneTC() throws IOException {
        new P01_LoginPage(driver)
                .enterUsername(DataUtils.getJsonData("validLogin", "username"))
                .enterPassword(DataUtils.getJsonData("validLogin", "password"))
                .clickOnLoginButton()
                .addRandomProducts(3, 6)
                .clickOnCartIcon()
                .clickOnChechOut()
                .fillingData(DataUtils.getJsonData("Information", "fname"), DataUtils.getJsonData("Information", "lname"), zipf)
                .clickOnContinue();
        Assert.assertTrue(Utility.verifyURL(driver, DataUtils.getPropertyValue("Environments", "CheckOutURL")));
    }

    @AfterMethod
    public void quit() {
        driver.quit();
    }
}
