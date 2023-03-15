package tests;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
public class CheckoutTest extends BaseTest {
    @Test(description = "Successful checkout", retryAnalyzer = Retry.class)
    public void successfulCheckout(){
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        productsPage.openCart();
        cartPage.checkout();
        checkoutPage.checkoutContinue("firstName", "lastName", "postalCode");
        String title = driver.findElement(By.cssSelector(".title")).getText();
        assertEquals(title, "Checkout: Overview", "Wrong error message");
    }

    @DataProvider(name = "Входящие данные для негативных тестов")
    public Object[][] getDataForCheckout(){
        return new Object[][]{
                {"", "test", "12345", "Error: First Name is required"},
                {"test", "", "12345", "Error: Last Name is required"},
                {"test", "test", "", "Error: Postal Code is required"},
        };
    }

    @Test(description = "Unsuccessful checkout", dataProvider = "Входящие данные для негативных тестов",
            retryAnalyzer = Retry.class)
    public void negativeCheckout(String firstname, String lastname, String zipCode, String expectedError){
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        productsPage.openCart();
        cartPage.checkout();
        checkoutPage.checkoutContinue(firstname, lastname, zipCode);
        String error = checkoutPage.getErrorMessage();
        assertEquals(error, expectedError, "Wrong error message");
    }

    @Test(description = "Check cancel button return to cart", retryAnalyzer = Retry.class)
    public void cancelButton(){
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        productsPage.openCart();
        cartPage.checkout();
        checkoutPage.cancelCheckout();
        String title = driver.findElement(By.cssSelector(".title")).getText();
        assertEquals(title, "Your Cart", "Wrong error message");
    }

}
