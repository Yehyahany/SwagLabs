import Pages.P01_LoginPage;
import Utilities.DataUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class TC01_LoginTest {
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
    public void ValidLoginTC() throws IOException {
        new P01_LoginPage(driver)
                .enterUsername(DataUtils.getJsonData("validLogin", "username"))
                .enterPassword(DataUtils.getJsonData("validLogin", "password"))
                .clickOnLoginButton();
        Assert.assertTrue(new P01_LoginPage(driver).assertLogin(DataUtils.getPropertyValue("Environments", "HomeURL")));
    }

    @AfterMethod
    public void quit() {
        driver.quit();
    }
}
