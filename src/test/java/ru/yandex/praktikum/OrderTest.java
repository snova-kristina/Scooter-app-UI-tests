package ru.yandex.praktikum;

import org.junit.Test;

public class OrderTest extends BaseTest {

	@Test
	public void orderCompleteFromHeader() {
		MainPageObject objMainPage = new MainPageObject(webDriver);
		objMainPage.clickHeaderOrderButton();
		OrderPageObject objOrderPage = new OrderPageObject(webDriver);
		objOrderPage.waitForLoadOrderPage();
		objOrderPage.fillRecipientBlock("Иван", "Иванов", "Невского 5", "89001234567");
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
		objMainPage.clickBodyOrderButton();
		OrderPageObject objOrderPage = new OrderPageObject(webDriver);
		objOrderPage.waitForLoadOrderPage();
		objOrderPage.fillRecipientBlock("Тест", "Тестов", "Ленина 2, 1", "+79001234567");
		objOrderPage.setDeliveryDate();
		objOrderPage.setRentalPeriod();
		objOrderPage.setBlackScooterColor();
		objOrderPage.setComment("Другой комментарий");
		objOrderPage.clickOrderButton();
		objOrderPage.confirmOrder();
		objOrderPage.checkOrderCreation();
	}
}