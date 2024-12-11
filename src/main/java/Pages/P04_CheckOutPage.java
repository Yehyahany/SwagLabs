package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static Utilities.Utility.generalWait;

public class P04_CheckOutPage {
    private final WebDriver driver;
    private final By firstname = By.id("first-name");
    private final By lastname = By.id("last-name");
    private final By zipcode = By.id("postal-code");
    private final By continueButton = By.className("cart_button");


    public P04_CheckOutPage(WebDriver driver) {
        this.driver = driver;
    }

    public P04_CheckOutPage fillingData(String FName, String LName, String ZCode) {
        Utility.sendData(driver, firstname, FName);
        Utility.sendData(driver, lastname, LName);
        Utility.sendData(driver, zipcode, ZCode);
        return this;
    }

    public P05_OverviewPage clickOnContinue() {
        Utility.clickOnElement(driver, continueButton);
        return new P05_OverviewPage(driver);
    }

    public boolean verifyURL(String expectedURL) {
        try {
            generalWait(driver).until(ExpectedConditions.urlToBe(expectedURL));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
