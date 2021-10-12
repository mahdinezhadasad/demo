package com.example.selenium.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstExample {

    public static WebDriver driver;

    @BeforeAll
	public static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}


	@BeforeEach
	public void beforeEach() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
		driver = new ChromeDriver(options);
	}

    @AfterEach
	public void afterEach()  {
		if (driver != null) {
			driver.quit();
		}
	}

    @Test
	public void testGetGoogleTitle() {
		driver.get("https://www.google.com/");
		System.out.println("GOOGLE PAGE TITLE:" + driver.getTitle());
		assertEquals("Google", driver.getTitle());
	}

}
