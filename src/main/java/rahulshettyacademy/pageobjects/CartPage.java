package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); // this is how the PageFactoring knows the driver
	}

	//Properties of this class
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	//PageFactory	
		
	@FindBy(css=".totalRow button") //This will construct like this during runtime driver.findElement(By.id("userEmail"))
	private WebElement checkOutButton; // and then places in this variable
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;
	
	
	//Action Methods
	public Boolean VerifyProductDisplay(String productName) {
		Boolean match = cartProducts.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage goToCheckout() {
		checkOutButton.click();
		return new CheckOutPage(driver);
		
	}
	
	
}
