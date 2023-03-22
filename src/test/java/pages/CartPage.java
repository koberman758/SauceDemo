package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    public static final By CHECKOUT_BUTTON = By.id("checkout");
    public static final By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");
    String removeItemXpath = "//*[text()='%s']/ancestor::*[contains(@class, 'cart_item')]//button";

    public CartPage(WebDriver driver) {
        super(driver);
    }

   @Step("Opening cart page")
    public void open() {
        driver.get(BASE_URL + "/cart.html");
    }

    @Step("Opening Checkout page")
    public void checkout() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    @Step("Removing item from the cart")
    public void removeItem(String item) {
        By removeItemLocator = By.xpath(String.format(removeItemXpath, item));
        driver.findElement(removeItemLocator).click();
    }

    @Step("Continue shopping")
    public void continueShopping() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }
}
