package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class MyOrdersPage extends AbstractComponents {

	WebDriver driver;
	
	public MyOrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
		
	@FindBy(css=".table tr td:nth-child(3)") 
	private List<WebElement> productsInTheTeble; 

	//Action Methods
	public Boolean VerifyOrdersDisplay(String productName) {
		Boolean match = productsInTheTeble.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
	
}
