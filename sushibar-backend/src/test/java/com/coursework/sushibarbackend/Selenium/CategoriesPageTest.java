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
import java.util.List;

public class CategoriesPageTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://localhost:3000/categories");
    }

    @Test
    public void testCategoriesLoading() {
        // Ждем, пока загрузка завершится
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".react-loading-skeleton")));

        // Проверяем, что карточки категорий отображаются
        List<WebElement> categoryCards = driver.findElements(By.cssSelector(".card"));
        Assert.assertTrue(categoryCards.size() > 0, "Карточки категорий должны отображаться");
    }

    @Test
    public void testCategoryCardContent() {
        // Ждем, пока карточки загрузятся
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card")));

        // Проверяем первую карточку
        WebElement firstCard = driver.findElement(By.cssSelector(".card"));
        WebElement title = firstCard.findElement(By.cssSelector(".card-title"));
        WebElement description = firstCard.findElement(By.cssSelector(".card-text"));
        WebElement button = firstCard.findElement(By.cssSelector("a.btn-primary"));

        // Проверяем, что элементы карточки существуют и содержат текст
        Assert.assertTrue(title.isDisplayed(), "Название категории должно отображаться");
        Assert.assertTrue(description.isDisplayed(), "Описание категории должно отображаться");
        Assert.assertTrue(button.isDisplayed(), "Кнопка 'Посмотреть товары' должна отображаться");

        Assert.assertFalse(title.getText().isEmpty(), "Название категории не должно быть пустым");
        Assert.assertFalse(description.getText().isEmpty(), "Описание категории не должно быть пустым");
    }

    @Test
    public void testCategoryRedirect() {
        // Ждем, пока карточки загрузятся
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card")));

        // Нажимаем на кнопку "Посмотреть товары" первой категории
        WebElement firstCardButton = driver.findElement(By.cssSelector(".card a.btn-primary"));
        firstCardButton.click();

        // Проверяем, что редирект произошел на правильный URL
        wait.until(ExpectedConditions.urlContains("/catalog/"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/catalog/"), "Должен быть редирект на страницу каталога");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

