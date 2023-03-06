package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ProductPurchaseTest extends BaseTest {

    @Test
    public void successfulPurchase() {

        loginPage.open();
        loginPage.login(USER, PASSWORD);
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.openCart();
        cartPage.checkout();

        checkoutPage.checkoutContinue("name", "lname", "12345");
        driver.findElement(By.id("finish")).click();
        String complete = driver.findElement(By.cssSelector(".title")).getText();
        assertEquals(complete, "Checkout: Complete!", "покупка не завершена");



        /*
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String title = driver.findElement(By.cssSelector(".title")).getText();
        assertEquals(title, "Products", "пользователь не зарегистрирован");
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        String shoppingcartbadge = driver.findElement(By.cssSelector(".shopping_cart_badge")).getText();
        assertEquals(shoppingcartbadge, "1", "товар не добавлен в корзину");
        driver.findElement(By.className("shopping_cart_link")).click();
        String quantity = driver.findElement(By.className("cart_quantity")).getText();
        assertEquals(quantity, "1", "incorrect quantity");
        driver.findElement(By.id("checkout")).click();
        String titleCheckout = driver.findElement(By.cssSelector(".title")).getText();
        assertEquals(titleCheckout, "Checkout: Your Information", "ERROR");
        driver.findElement(By.id("first-name")).sendKeys("test");
        driver.findElement(By.id("last-name")).sendKeys("test");
        driver.findElement(By.id("postal-code")).sendKeys("123456");
        driver.findElement(By.id("continue")).click();
        String overview = driver.findElement(By.cssSelector(".title")).getText();
        assertEquals(overview, "Checkout: Overview", "Невозможно перейти к оформлению заказа");
        driver.findElement(By.id("finish")).click();
        String complete = driver.findElement(By.cssSelector(".title")).getText();
        assertEquals(complete, "Checkout: Complete!", "покупка не завершена");
*/
    }


}
