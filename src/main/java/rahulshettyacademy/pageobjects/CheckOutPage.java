package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {

	WebDriver driver;

	// Actions a = new Actions(driver);
	public CheckOutPage(WebDriver driver) {
		super(driver);

		this.driver = driver;
		PageFactory.initElements(driver, this); // this is how the PageFactoring knows the driver
	}

	@FindBy(css = "[placeholder='Select Country']") // This will construct like this during runtime
													// driver.findElement(By.id("userEmail"))
	private WebElement selectCountryInput; // and then places in this variable

	@FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
	private WebElement firstCountryOption;

	@FindBy(css = "[class*='action__submit']")
	private WebElement submit;

	By visibleCountries = By.className("ta-results");

	// Action Methods
	public void SelectCountry(String yourCountry) {
		Actions a = new Actions(driver);
		a.sendKeys(selectCountryInput, yourCountry).build().perform();
		waitForElementToAppear(visibleCountries);
		firstCountryOption.click();

	}

	public OrderPage submitOrder() {
		submit.click();

		return new OrderPage(driver);
	}

}
