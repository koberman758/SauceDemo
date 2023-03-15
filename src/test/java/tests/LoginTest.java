package tests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
public class LoginTest extends BaseTest{

    @Test(description = "Check if user can login", retryAnalyzer = Retry.class)
    public void successfulLogin(){
        loginPage.open();
        loginPage.login(USER, PASSWORD);
        String title = productsPage.getTitle();
        assertEquals(title, "Products", "пользователь не зарегистрирован");
    }

    @DataProvider(name = "Входящие данные для негативных тестов на логин")
    public Object[][] getDataForLogin(){
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

   /* @Test(description = "user name should be required")
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
    */
}
