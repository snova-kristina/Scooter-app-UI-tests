package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class OrderTest {

	private WebDriver webDriver;

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

	@After
	public void tearDown() {
		webDriver.quit();
	}

	@Test
	public void orderCompleteFromHeader() {
		MainPageObject objMainPage = new MainPageObject(webDriver);
		objMainPage.openPage();
		objMainPage.allowCookie();
		objMainPage.clickHeaderOrderButton();
		OrderPageObject objOrderPage = new OrderPageObject(webDriver);
		objOrderPage.waitForLoadOrderPage();
		objOrderPage.fillRecipientBlock();
		objOrderPage.setDeliveryDate();
		objOrderPage.setRentalPeriod();
		objOrderPage.setGreyScooterColor();
		objOrderPage.setComment("Комментарий");
		objOrderPage.clickOrderButton();
		objOrderPage.confirmOrder();
		objOrderPage.checkOrderCreation();
	}

	@Test
	public void orderCompleteFromBody() {
		MainPageObject objMainPage = new MainPageObject(webDriver);
		objMainPage.openPage();
		objMainPage.allowCookie();
		objMainPage.clickBodyOrderButton();
		OrderPageObject objOrderPage = new OrderPageObject(webDriver);
		objOrderPage.waitForLoadOrderPage();
		objOrderPage.fillRecipientBlock();
		objOrderPage.setDeliveryDate();
		objOrderPage.setRentalPeriod();
		objOrderPage.setBlackScooterColor();
		objOrderPage.setComment("Другой комментарий");
		objOrderPage.clickOrderButton();
		objOrderPage.confirmOrder();
		objOrderPage.checkOrderCreation();
	}
}