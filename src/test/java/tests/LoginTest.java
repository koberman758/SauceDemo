package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.LoginPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void successfulLogin() {

        loginPage.open();
        loginPage.login(USER, PASSWORD);
        String title = productsPage.getTitle();
        assertEquals(title, "Products", "пользователь не зарегистрирован");

    }

    @Test
    public void usernameIsRequired() {
        loginPage.open();
        loginPage.login("", "");
        String error = loginPage.getErrorMessage();
        assertEquals(error, "Epic sadface: Username is required", "wrong error message");
    }

    @Test
    public void passwordIsRequired() {
        loginPage.open();
        loginPage.login("standard_use", "");
        String error = loginPage.getErrorMessage();
        assertEquals(error, "Epic sadface: Password is required", "wrong error message");
    }

    @Test
    public void incorrectUsernameAndPassword() {
        loginPage.open();
        loginPage.login("test", "test");
        String error = loginPage.getErrorMessage();
        assertEquals(error, "Epic sadface: Username and password do not match any user in this service",
                "wrong error message");

    }

    @Test
    public void lockedOutUser() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");
        String error = loginPage.getErrorMessage();
        assertEquals(error, "Epic sadface: Sorry, this user has been locked out.",
                "wrong error message");
    }


}
