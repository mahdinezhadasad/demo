package com.example.selenium.demo;

import com.example.selenium.demo.pages.GoogleSearchPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ThirdExample {
    public static WebDriver driver;

    @BeforeAll
	public static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
		driver = new ChromeDriver(options);
	}

    @AfterEach
	public void afterEach()  {
		if (driver != null) {
			driver.quit();
		}
	}

    @Test
	public void testGetGoogleResults() {
        GoogleSearchPage googleSearchPage = new GoogleSearchPage( driver);

		driver.get("https://www.google.com/");
		googleSearchPage.clickCookieButton();
		assertEquals("Google", googleSearchPage.title());

        googleSearchPage.search("Selenium");
        googleSearchPage.submit();

        assertTrue(googleSearchPage.showAllResults() > 0);
	}
}
