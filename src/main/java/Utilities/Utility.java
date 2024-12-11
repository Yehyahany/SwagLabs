package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Utility {


    private static final String SCREENSHOTS_PATH = "src/test-outputs/Screenshots/";

    public static void clickOnElement(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    public static void sendData(WebDriver driver, By locator, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(text);
    }


    public static String GetText(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    public static WebDriverWait generalWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public static void scrolling(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", findWebElement(driver, locator));
    }

    public static WebElement findWebElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

//    public static String getTimestamp() {
//        return new SimpleDateFormat("yyyy-MM-dd-h-m-ssa").format(new Date());
//    }

//    public static void takeScreenShot(WebDriver driver,String screenshotName){
//        try{
//            //Capture ScreenShot using takeScreenShot
//            File screenshotSrc = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//
//            //Save SS to file
//            File screenshotFile = new File(SCREENSHOTS_PATH + screenshotName + "-" + getTimestamp() + ".png");
//            FileUtils.copyFile(screenshotSrc,screenshotFile);
//
//            //Attach the ss to allure
//            Allure.addAttachement(screenshotName, Files.newInputStream(Path.of(screenshotFile.getPath())));
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    public static void selectingfromDropDown(WebDriver driver, By locator, String option) {
        new Select(findWebElement(driver, locator)).selectByVisibleText(option);
    }


    public static int generateRandomNumber(int upperBound) {
        return new Random().nextInt(upperBound) + 1;
    }

    public static Set<Integer> generateUniqueNumber(int NumberOfProductsNeeded, int totalnumber) {
        Set<Integer> generatedNumber = new HashSet<>();
        while (generatedNumber.size() < NumberOfProductsNeeded) {
            int randomNumber = generateRandomNumber(totalnumber);
            generatedNumber.add(randomNumber);
        }
        return generatedNumber;
    }

    public static boolean verifyURL(WebDriver driver, String expectedURL) {
        try {
            generalWait(driver).until(ExpectedConditions.urlToBe(expectedURL));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
