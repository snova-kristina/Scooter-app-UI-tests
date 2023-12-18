package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class BaseTest {

	protected WebDriver webDriver;

	@Before
	public void setup() {
		switch (System.getProperty("browser")) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--remote-allow-origins=*");
				this.webDriver = new ChromeDriver(options);
				break;
			case "firefox":
			default:
				WebDriverManager.firefoxdriver().setup();
				this.webDriver = new FirefoxDriver();
		}
	}

	@Before
	public void openPage() {
		MainPageObject objMainPage = new MainPageObject(webDriver);
		objMainPage.openPage();
		objMainPage.allowCookie();
	}

	@After
	public void tearDown() {
		webDriver.quit();
	}
}
