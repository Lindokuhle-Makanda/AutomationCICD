package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); // this is how the PageFactoring knows the driver
	}

	//Properties of this class
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	//PageFactory	
		
	@FindBy(id="userEmail") //This will construct like this during runtime driver.findElement(By.id("userEmail"))
	private WebElement userEmail; // and then places in this variable
	
	@FindBy(id="userPassword")
	private WebElement userPassword;
	
	@FindBy(id="login")
	private WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	private WebElement errorMessage;
	
	
	
	//Action Methods
	public ProductCatalogue loginApp(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		return  new ProductCatalogue(driver);
	}
	
	public String getErorMsg() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	

	
}
