package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPageObject {

	private final WebDriver webDriver;

	public MainPageObject(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	//кнопка принять куки
	private By confirmCookieButton = By.id("rcc-confirm-button");
	//кнопка "Заказать" в шапке
	private By headerOrderButton = By.className("Button_Button__ra12g");
	//кнопка "Заказать" в теле
	private By bodyOrderButton = By.className("Button_Middle__1CSJM");
	//список FAQ
	private By accordionBlock = By.className("accordion__item");
	//элемент списка FAQ- вопрос
	private String accordionHeader = "//div[@id='accordion__heading-%s']";
	//элемент списка FAQ- ответ
	private String accordionPanel = "//div[@id='accordion__panel-%s']";

	//метод открытия главной страницы
	public void openPage() {
		webDriver.get("https://qa-scooter.praktikum-services.ru");
	}

	//метод для принятия кук
	public void allowCookie() {
		webDriver.findElement(confirmCookieButton).click();
	}

	//метод нажатия на кнопку "Заказать"
	public void clickHeaderOrderButton() {
		webDriver.findElement(headerOrderButton).click();
	}

	//метод нажатия на кнопку "Заказать"
	public void clickBodyOrderButton() {
		WebElement element = webDriver.findElement(bodyOrderButton);
		((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", element);
		element.click();
	}

	//метод для скролла до блока FAQ
	public void scrollToFaq() {
		WebElement element = webDriver.findElement(accordionBlock);
		((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", element);
	}

	//метод для получения текста элемента - вопроса
	public String getAccordionQuestion(String elementText) {
		return webDriver.findElement(By.xpath(String.format(accordionHeader, elementText))).getText();
	}

	//метод для получения текста элемента - ответа
	public String getAccordionAnswer(String elementText) {
		webDriver.findElement(By.xpath(String.format(accordionHeader, elementText))).click();
		return webDriver.findElement(By.xpath(String.format(accordionPanel, elementText))).getText();
	}
}
