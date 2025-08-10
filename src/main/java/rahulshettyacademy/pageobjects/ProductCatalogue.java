package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {

	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); // this is how the PageFactoring knows the driver
	}

	//Properties of this class
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	//PageFactory	
		
	@FindBy(css=".mb-3") //This will construct like this during runtime driver.findElement(By.id("userEmail"))
	private List<WebElement> products; // and then places in this variable
	@FindBy(css=".ng-animating")
	private WebElement spinner;
	
	By productBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		return getProductList().stream()
				.filter(p -> p.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToDisappear();
		waitForElementToAppear(toastMessage);
		
	}
	
}
