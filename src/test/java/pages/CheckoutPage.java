package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
    public static final By FIRST_NAME = By.id("first-name");
    public static final By LAST_NAME = By.id("last-name");
    public static final By POSTAL_CODE = By.id("postal-code");
    public static final By CANCEL_BUTTON = By.id("cancel");
    public static final By CONTINUE_BUTTON = By.id("continue");
    public static final By ERROR_MESSAGE = By.cssSelector("[data-test=error]");
    public static final By FINISH_BUTTON = By.id("finish");
    public static final By BACK_TO_PRODUCTS_BUTTON = By.id("back-to-products");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void checkoutContinue(String name, String lastName, String postalCode) {
        driver.findElement(FIRST_NAME).sendKeys(name);
        driver.findElement(LAST_NAME).sendKeys(lastName);
        driver.findElement(POSTAL_CODE).sendKeys(postalCode);
        driver.findElement(CONTINUE_BUTTON).click();
    }

    public void cancelCheckout() {
        driver.findElement(CANCEL_BUTTON).click();
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    public void finishButton() {
        driver.findElement(FINISH_BUTTON).click();
    }

    public void backToProductsButton() {
        driver.findElement(BACK_TO_PRODUCTS_BUTTON).click();
    }


}
