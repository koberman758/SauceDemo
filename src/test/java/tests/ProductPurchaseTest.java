package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ProductPurchaseTest extends BaseTest {
    @Test(description = "SuccessfulPurchase")
    public void successfulPurchase() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.openCart();
        cartPage.checkout();
        checkoutPage.checkoutContinue("name", "lname", "12345");
        checkoutPage.finishButton();
        String complete = checkoutPage.getTitle();
        assertEquals(complete, "Checkout: Complete!", "покупка не завершена");
    }

    @Test(description = "Button 'back home' return to products page")
    public void backHomeButton() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.openCart();
        cartPage.checkout();
        checkoutPage.checkoutContinue("name", "lname", "12345");
        checkoutPage.finishButton();
        checkoutPage.backToProductsButton();
        String title =checkoutPage.getTitle();
        assertEquals(title, "Products", "Wrong error message");
    }
}
