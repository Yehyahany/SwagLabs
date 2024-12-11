package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class P02_LandingPage {
    static float totalPrice = 0;
    private static List<WebElement> allproducts;
    private static List<WebElement> selectedproducts;
    private final WebDriver driver;
    private final By addtoCartAll = By.xpath("//button[@class]");
    private final By numofinventory = By.className("shopping_cart_badge");
    private final By numofSelectedItems = By.xpath("//button[.='REMOVE']");
    private final By cartIcon = By.className("shopping_cart_link");
    private final By pricesOfSelectedProductsLocator = By.xpath("//button[.='REMOVE'] //preceding-sibling::div[@class='inventory_item_price']");

    public P02_LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    public P02_LandingPage addAllProductsToCart() {
        allproducts = driver.findElements(addtoCartAll);
        for (int i = 1; i <= allproducts.size(); i++) {
            By addtoCartAll = By.xpath("(//button[@class])[" + i + "]");
            Utility.clickOnElement(driver, addtoCartAll);
        }
        return this;
    }

    public String getNumofSelectedProducts() {
        try {
            selectedproducts = driver.findElements(numofSelectedItems);
            return String.valueOf(selectedproducts.size());
        } catch (Exception e) {
            System.out.println("No selected elements");
            return "0";
        }
    }


    public String getNumOfProductsinCart() {
        try {
            return Utility.GetText(driver, numofinventory);
        } catch (Exception e) {
            System.out.println("No such element");
            return "0";
        }
    }


    public P02_LandingPage addRandomProducts(int numberOfProductsNeeded, int totalNumberOfProducts) {
        Set<Integer> randomNumber = Utility.generateUniqueNumber(numberOfProductsNeeded, totalNumberOfProducts);
        for (int random : randomNumber) {
            By addtoCartAll = By.xpath("(//button[@class])[" + random + "]");
            Utility.clickOnElement(driver, addtoCartAll);
        }
        return this;
    }


    public String getTotalPriceOfSelectedProducts() {
        try {
            List<WebElement> pricesOfSelectedProducts = driver.findElements(pricesOfSelectedProductsLocator);
            for (int i = 1; i <= pricesOfSelectedProducts.size(); i++) {
                By elements = By.xpath("(//button[.='REMOVE'] //preceding-sibling::div[@class='inventory_item_price'])[" + i + "]");
                String fulltext = Utility.GetText(driver, elements);
                totalPrice += Float.parseFloat(fulltext.replace("$", ""));
                System.out.println("total price P02= " + totalPrice);
            }
            return String.valueOf(totalPrice);
        } catch (Exception e) {
            System.out.println("Failed summation@P02");
            return "0";
        }
    }

    public boolean compare() {
        return getNumOfProductsinCart().equals(getNumofSelectedProducts());
    }

    public P03_CartPage clickOnCartIcon() {
        Utility.clickOnElement(driver, cartIcon);
        return new P03_CartPage(driver);
    }
}
