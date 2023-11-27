package ru.yandex.praktikum;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPageObject {

	private final WebDriver webDriver;
	private final WebDriverWait webDriverWait;

	public OrderPageObject(WebDriver webDriver) {
		this.webDriver = webDriver;
		this.webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
	}

	//элементы блока "Для кого самокат"
	private By orderBlock = By.className("Order_Header__BZXOb");
	//поле "Имя"
	private By nameField = By.xpath("//input[@placeholder='* Имя']");
	//поле "Фамилия"
	private By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
	//поле "Адрес"
	private By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
	//выпадающий список "Станция метро"
	private By subwayField = By.xpath("//input[@placeholder='* Станция метро']");
	//поле "Телефон"
	private By phoneNumberField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
	//кнопка "Далее"
	private By orderNextButton = By.className("Button_Middle__1CSJM");

	//элементы блока "Про аренду"
	//поле "Когда привезти самокат"
	private By deliveryDateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
	//кнопка "след. месяц"
	private By dataPickerNextButton = By.className("react-datepicker__navigation--next");
	//день месяца
	private By dataPickerDay = By.className("react-datepicker__week");
	//поле срок аренды
	private By rentalPeriodField = By.className("Dropdown-placeholder");
	//выпадающий список срок аренды
	private By rentalPeriod = By.className("Dropdown-menu");
	//элемент списка срок аренды
	private By rentalPeriodVariant = By.xpath(".//div[text()='двое суток']");
	//чекбокс "чёрный жемчуг"
	private By blackScooterColor = By.id("black");
	//чекбокс "серая безысходность"
	private By greyScooterColor = By.id("grey");
	//поле "комментарий"
	private By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
	//кнопка "Заказать"
	private By orderButton = By.xpath("//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
	//всплывающее окно подтверждения заказа
	private By confirmationPopup = By.className("Order_Modal__YZ-d3");
	//кнопка "Да" в всплывающем окне
	private By confirmOrderButton = By.xpath("//div[@class='Order_Modal__YZ-d3']/div/button[text()='Да']");
	//всплывающее окно с сообщением об успешном создании заказа
	private By successOrderPopup = By.className("Order_Modal__YZ-d3");
	//кнопка Статус заказа
	private By orderStatusButton = By.xpath("//div[@class='Order_Modal__YZ-d3']/div/button");
	//сообщение об успешном заказе
	private By orderStatusHeader = By.className("Order_ModalHeader__3FDaJ");


	//методы для работы с элементами блока "Для кого самокат"
	public void waitForLoadOrderPage() {
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(orderBlock));
	}

	public void setName(String name) {
		webDriver.findElement(nameField).sendKeys(name);
	}

	public void setSurname(String surname) {
		webDriver.findElement(surnameField).sendKeys(surname);
	}

	public void setAddress(String address) {
		webDriver.findElement(addressField).sendKeys(address);
	}

	public void setSubway() {
		webDriver.findElement(subwayField).click();
		webDriver.findElement(subwayField).sendKeys(Keys.DOWN, Keys.ENTER);
	}

	public void setPhoneNumber(String phoneNumber) {
		webDriver.findElement(phoneNumberField).sendKeys(phoneNumber);
	}

	public void clickNextButton() {
		webDriver.findElement(orderNextButton).click();
	}

	//методы для работы с элементами блока "Про аренду"
	public void setDeliveryDate() {
		webDriver.findElement(deliveryDateField).click();
		webDriver.findElement(dataPickerNextButton).click();
		webDriver.findElement(dataPickerDay).click();
	}

	public void setRentalPeriod() {
		webDriver.findElement(rentalPeriodField).click();
		webDriver.findElement(rentalPeriod).isEnabled();
		webDriver.findElement(rentalPeriodVariant).click();
	}

	public void setGreyScooterColor() {
		webDriver.findElement(greyScooterColor).click();
	}

	public void setBlackScooterColor() {
		webDriver.findElement(blackScooterColor).click();
	}

	public void setComment(String comment) {
		webDriver.findElement(commentField).sendKeys(comment);
	}

	public void clickOrderButton() {
		webDriver.findElement(orderButton).click();
	}

	public void confirmOrder() {
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(confirmationPopup));
		webDriver.findElement(confirmOrderButton).click();
	}

	public void checkOrderCreation() {
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(successOrderPopup));
		webDriver.findElement(orderStatusButton).isEnabled();
		String popupText = webDriver.findElement(orderStatusHeader).getText();
		Assert.assertTrue(popupText.contains("Заказ оформлен"));
	}

	public void fillRecipientBlock() {
		setName("Пользователь");
		setSurname("Тестовый");
		setAddress("Ленина ул., 2, кв. 1");
		setSubway();
		setPhoneNumber("+79001234567");
		clickNextButton();
	}
}
