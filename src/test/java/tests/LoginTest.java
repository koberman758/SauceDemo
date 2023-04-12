package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.PropertyReader;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(description = "Check if user can login")
    public void successfulLogin() {
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        String title = productsPage.getTitle();
        assertEquals(title, "Products", "пользователь не зарегистрирован");
    }

    @Test(description = "Check if user can login")
    public void successfulLoginUsingSecrets() {
        loginPage.open();
        String login = System.getProperty("user", PropertyReader.getProperty("user"));
        loginPage.login(USER, PASSWORD);
        String title = productsPage.getTitle();
        assertEquals(title, "Products", "пользователь не зарегистрирован");
    }

    @DataProvider(name = "Входящие данные для негативных тестов на логин")
    public Object[][] getDataForLogin() {
        return new Object[][]{
                {"", "", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
        };
    }

    @Test(description = "User name should be required", dataProvider = "Входящие данные для негативных тестов на логин")
    public void negativeLogin(String username, String password, String expectedError) {
        loginPage.open();
        loginPage.login(username, password);
        String error = loginPage.getErrorMessage();
        assertEquals(error, expectedError, "wrong error message");
    }
}
