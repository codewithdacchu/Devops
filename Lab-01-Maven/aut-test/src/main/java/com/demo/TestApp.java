package com.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestApp {

    public static void main(String[] args) throws Exception {

        // Setup driver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Open app
        driver.get("http://localhost:8080");
        Thread.sleep(2000);

        // 🔐 LOGIN
        driver.findElement(By.id("user")).sendKeys("admin");
        driver.findElement(By.id("pass")).sendKeys("1234");
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

        Thread.sleep(2000);

        // ➕ CLICK "ADD TICKET"
        driver.findElement(By.xpath("//button[contains(text(),'Add Ticket')]")).click();
        Thread.sleep(1000);

        // 📝 ENTER TICKET
        driver.findElement(By.id("ticket")).sendKeys("Selenium Ticket");

        // ✅ SUBMIT
        driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
        Thread.sleep(2000);

        // 📄 VIEW TICKETS
        driver.findElement(By.xpath("//button[contains(text(),'View Tickets')]")).click();
        Thread.sleep(2000);

        // ✅ ASSERTION (VERY IMPORTANT)
        if (driver.getPageSource().contains("Selenium Ticket")) {
            System.out.println("✅ TEST PASSED");
        } else {
            System.out.println("❌ TEST FAILED");
        }

        driver.quit();
    }
}