package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Utilities.Utility.findWebElement;

public class P06_FinishOrderPage {
    private final WebDriver driver;
    private final By thanksMessage = By.className("complete-header");

    public P06_FinishOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean CheckVisibilityOfThanksMessage() {
        return findWebElement(driver, thanksMessage).isDisplayed();
    }
}
