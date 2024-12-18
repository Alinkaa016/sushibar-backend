package com.coursework.sushibarbackend.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;
public class LoginPageTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://localhost:3000/login");
    }

    @Test
    public void test1SuccessfulLogin() {
        WebElement usernameField = driver.findElement(By.id("formUsername"));
        WebElement passwordField = driver.findElement(By.id("formPassword"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

        usernameField.clear();
        passwordField.clear();

        // Ввод корректных данных
        usernameField.sendKeys("movavi");
        passwordField.sendKeys("Zxqw1234!");
        loginButton.click();

        // Ждем редиректа на главную страницу
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));

        // Проверяем, что редирект произошел
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:3000/");
    }


    @Test
    public void testInvalidLogin() {
        driver.get("http://localhost:3000/login");
        try {
            Thread.sleep(2000); // Ожидание в 2 секунды
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Найти поля для ввода логина и пароля
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

        usernameField.clear();
        passwordField.clear();

        // Ввести невалидные данные
        usernameField.sendKeys("invalidUser");
        passwordField.sendKeys("Qwerty1234!!!");
        loginButton.click();

        // Проверить отображение сообщения об ошибке
        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert")));
        assertTrue("Ожидается сообщение об ошибке", alert.getText().contains("Неверный логин или пароль"));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

