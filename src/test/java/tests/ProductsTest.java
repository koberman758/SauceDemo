package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ProductsTest extends BaseTest {
    @Test(description = "product added to cart", retryAnalyzer = Retry.class)
    public void productAddedToCart() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        productsPage.addToCart("Sauce Labs Backpack");
        String counterOfCart = driver.findElement(By.cssSelector(".shopping_cart_badge")).getText();
        assertEquals(counterOfCart, "1", "Item not added");
    }

    @Test(description = "product removed from cart", retryAnalyzer = Retry.class)
    public void productRemovedFromCart() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Sauce Labs Bolt T-Shirt");
        productsPage.removeFromCart("Sauce Labs Backpack");
        String counterOfCart = driver.findElement(By.cssSelector(".shopping_cart_badge")).getText();
        assertEquals(counterOfCart, "1", "Item not added");
    }

}
