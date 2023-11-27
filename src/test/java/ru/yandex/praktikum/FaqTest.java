package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FaqTest {
	private WebDriver webDriver;
	private final String expectedAccordionHeader;
	private final String expectedAccordionPanel;
	private final String index;

	public FaqTest(String index, String expectedAccordionHeader, String expectedAccordionPanel) {
		this.index = index;
		this.expectedAccordionHeader = expectedAccordionHeader;
		this.expectedAccordionPanel = expectedAccordionPanel;
	}

	@Parameterized.Parameters
	public static Object[][] getFaqElements() {
		return new Object[][]{
				{"0", "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
				{"1", "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
				{"2", "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
				{"3", "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
				{"4", "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
				{"5", "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
				{"6", "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
				{"7", "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
		};
	}

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
	public void whenClickOnQuestionRightAnswerIsDisplayed() {
		MainPageObject objMainPage = new MainPageObject(webDriver);
		objMainPage.openPage();
		objMainPage.allowCookie();
		objMainPage.scrollToFaq();
		String actualAccordionQuestion = objMainPage.getAccordionQuestion(index);
		String actualAccordionAnswer = objMainPage.getAccordionAnswer(index);
		assertEquals(expectedAccordionHeader, actualAccordionQuestion);
		assertEquals(expectedAccordionPanel, actualAccordionAnswer);
	}
}
