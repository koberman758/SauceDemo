package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CartTest extends BaseTest {

    @Test(description = "Remove product from cart")
    public void removeProductFromCart() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Sauce Labs Bolt T-Shirt");
        productsPage.addToCart("Sauce Labs Bike Light");
        productsPage.addToCart("Sauce Labs Fleece Jacket");
        productsPage.openCart();
        cartPage.removeItem("Sauce Labs Backpack");
        cartPage.removeItem("Sauce Labs Bolt T-Shirt");
        int numberOfProducts = driver.findElements(By.className("cart_item")).size();
        assertEquals(numberOfProducts, 2, "the product has not been removed");
    }
}
