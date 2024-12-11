package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P03_CartPage {
    static float totalPrice = 0;
    private final WebDriver driver;
    private final By pricesOfSelectedProductsLocator = By.xpath("//button[.='REMOVE'] // preceding-sibling::div[@class='inventory_item_price']");
    private final By CheckoutButton = By.className("checkout_button");

    public P03_CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTotalPrice() {
        try {
            List<WebElement> pricesOfSelectedProducts = driver.findElements(pricesOfSelectedProductsLocator);
            for (int i = 1; i <= pricesOfSelectedProducts.size(); i++) {
                By elements = By.xpath("(//button[.='REMOVE'] // preceding-sibling::div[@class='inventory_item_price'])[" + i + "]");
                String fulltext = Utility.GetText(driver, elements);
                totalPrice += Float.parseFloat(fulltext.replace("$", ""));
            }
            System.out.println("P03 Total price= " + totalPrice);
            return String.valueOf(totalPrice);
        } catch (Exception e) {
            System.out.println("failed summation@P03");
            return "0";
        }
    }

    public boolean comparinPrices(String price) {
        return getTotalPrice().equals(price);
    }

    public P04_CheckOutPage clickOnChechOut() {
        Utility.clickOnElement(driver, CheckoutButton);
        return new P04_CheckOutPage(driver);
    }

}
