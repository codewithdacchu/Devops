//comment added 
//Running in Demo Class Cluster B DevOps 

package com.demo;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestApp {

    WebDriver driver;

    @BeforeAll
    void setup() {

        // Automatically downloads matching ChromeDriver
        WebDriverManager.chromedriver().browserVersion("145").setup();

        ChromeOptions options = new ChromeOptions();

        // ✅ REQUIRED for Jenkins (headless execution)
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        // Optional (safe fallback if needed)
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
    }

    @AfterAll
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testLogin() throws Exception {

        driver.get("http://localhost:8080");
        Thread.sleep(1000);

        driver.findElement(By.id("user")).sendKeys("admin");
        driver.findElement(By.id("pass")).sendKeys("1234");
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

        Thread.sleep(1000);

        Assertions.assertTrue(driver.getPageSource().contains("Issue Tracker"));
    }

    @Test
    void testAddTicket() throws Exception {

        driver.get("http://localhost:8080");
        Thread.sleep(1000);

        // Login
        driver.findElement(By.id("user")).sendKeys("admin");
        driver.findElement(By.id("pass")).sendKeys("1234");
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

        Thread.sleep(1000);

        // Add Ticket
        driver.findElement(By.xpath("//button[contains(text(),'Add Ticket')]")).click();
        Thread.sleep(500);

        driver.findElement(By.id("ticket")).sendKeys("JUnit Ticket");
        driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();

        Thread.sleep(1000);

        Assertions.assertTrue(driver.getPageSource().contains("Added to DB"));
    }

    @Test
    void testViewTickets() throws Exception {

        driver.get("http://localhost:8080");
        Thread.sleep(1000);

        // Login
        driver.findElement(By.id("user")).sendKeys("admin");
        driver.findElement(By.id("pass")).sendKeys("1234");
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

        Thread.sleep(1000);

        // View Tickets
        driver.findElement(By.xpath("//button[contains(text(),'View Tickets')]")).click();

        Thread.sleep(1000);

        Assertions.assertTrue(driver.getPageSource().contains("["));
    }
}