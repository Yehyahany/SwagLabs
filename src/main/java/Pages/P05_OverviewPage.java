package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P05_OverviewPage {
    private final WebDriver driver;
    private final By subitemTotal = By.className("summary_subtotal_label");
    private final By tax = By.className("summary_tax_label");
    private final By total = By.className("summary_total_label");
    private final By finishButton = By.className("cart_button");

    public P05_OverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public Float get_subtotal() {
        return Float.parseFloat(Utility.GetText(driver, subitemTotal).replace("Item total: $", ""));
    }

    public Float get_tax() {
        return Float.parseFloat(Utility.GetText(driver, tax).replace("Tax: $", ""));
    }

    public Float get_total() {
        return Float.parseFloat(Utility.GetText(driver, total).replace("Total: $", ""));
    }

    public String calculateTotalPrice() {
        return String.valueOf(get_subtotal() + get_tax());
    }

    public boolean comparingPrices() {
        return calculateTotalPrice().equals(String.valueOf(get_total()));
    }

    public P06_FinishOrderPage clickOnFinishButton() {
        Utility.clickOnElement(driver, finishButton);
        return new P06_FinishOrderPage(driver);
    }
}
