package tests;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
public class ProductPurchaseTest extends BaseTest{
    @Test(description = "SuccessfulPurchase", retryAnalyzer = Retry.class)
    public void successfulPurchase(){
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.openCart();
        cartPage.checkout();
        checkoutPage.checkoutContinue("name", "lname", "12345");
        driver.findElement(By.id("finish")).click();
        String complete = driver.findElement(By.cssSelector(".title")).getText();
        assertEquals(complete, "Checkout: Complete!", "покупка не завершена");
    }

    @Test(description = "Button 'back home' return to products page", retryAnalyzer = Retry.class)
    public void backHomeButton(){
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.openCart();
        cartPage.checkout();
        checkoutPage.checkoutContinue("name", "lname", "12345");
        driver.findElement(By.id("finish")).click();
        driver.findElement(By.id("back-to-products")).click();
        String title = driver.findElement(By.cssSelector(".title")).getText();
        assertEquals(title, "Products", "Wrong error message");
    }

}
