import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void successfulLogin() {
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String title = driver.findElement(By.cssSelector(".title")).getText();
        assertEquals(title, "PRODUCTS", "пользователь не зарегистрирован");


    }

    @Test
    public void usernameIsRequired() {
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("login-button")).click();
        String error = driver.findElement(By.cssSelector("[data-test=error]")).getText();
        assertEquals(error, "Epic sadface: Username is required", "wrong error message");

    }

    @Test
    public void passwordIsRequired() {
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standart-user");
        driver.findElement(By.id("login-button")).click();
        String error = driver.findElement(By.cssSelector("[data-test=error]")).getText();
        assertEquals(error, "Epic sadface: Password is required", "wrong error message");
    }

    @Test
    public void incorrectUsernameAndPassword() {
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("test");
        driver.findElement(By.id("password")).sendKeys("test");
        driver.findElement(By.id("login-button")).click();
        String error = driver.findElement(By.cssSelector("[data-test=error]")).getText();
        assertEquals(error, "Epic sadface: Username and password do not match any user in this service",
                "wrong error message");

    }

    @Test
    public void lockedOutUser() {
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String error = driver.findElement(By.cssSelector("[data-test=error]")).getText();
        assertEquals(error, "Epic sadface: Sorry, this user has been locked out.",
                "wrong error message");
    }


}
