package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutTest extends BaseTest {
    @Test(description = "Successful checkout")
    public void successfulCheckout() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        productsPage.openCart();
        cartPage.checkout();
        checkoutPage.checkoutContinue("firstName", "lastName", "postalCode");
        String title = checkoutPage.getTitle();
        assertEquals(title, "Checkout: Overview", "Wrong error message");
    }

    @DataProvider(name = "Входящие данные для негативных тестов")
    public Object[][] getDataForCheckout() {
        return new Object[][]{
                {"", "test", "12345", "Error: First Name is required"},
                {"test", "", "12345", "Error: Last Name is required"},
                {"test", "test", "", "Error: Postal Code is required"},
        };
    }

    @Test(description = "Unsuccessful checkout", dataProvider = "Входящие данные для негативных тестов")
    public void negativeCheckout(String firstname, String lastname, String zipCode, String expectedError) {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        productsPage.openCart();
        cartPage.checkout();
        checkoutPage.checkoutContinue(firstname, lastname, zipCode);
        String error = checkoutPage.getErrorMessage();
        assertEquals(error, expectedError, "Wrong error message");
    }

    @Test(description = "Check cancel button return to cart")
    public void cancelButton() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        productsPage.openCart();
        cartPage.checkout();
        checkoutPage.cancelCheckout();
        String title = checkoutPage.getTitle();
        assertEquals(title, "Your Cart", "Wrong error message");
    }
}
