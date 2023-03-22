package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage{
    String addToCartXpath = "//*[text()='%s']/ancestor::*[contains(@class, 'inventory_item')]//button";
    String removeFromCartXpath = "//*[text()='%s']/ancestor::*[contains(@class, 'inventory_item')]//button";
    public static final By CART = By.id("shopping_cart_container");
    public static final By CART_BADGE = By.cssSelector(".shopping_cart_badge");

    public ProductsPage(WebDriver driver){
        super(driver);
    }

    @Step("Adding item to the cart")
    public void addToCart(String product){
        By addToCartLocator = By.xpath(String.format(addToCartXpath, product));
        driver.findElement(addToCartLocator).click();
    }

    @Step("Removing item from the cart")
    public void removeFromCart(String product){
        By removeFromCartLocator = By.xpath(String.format(removeFromCartXpath, product));
        driver.findElement(removeFromCartLocator).click();
    }

    @Step("Opening cart")
    public void openCart(){
        driver.findElement(CART).click();
    }

    @Step("Getting information about the quantity of items in the cart")
    public String cartBadge() {
        return driver.findElement(CART_BADGE).getText();
    }


}
