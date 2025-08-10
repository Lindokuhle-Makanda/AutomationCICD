package rahulshettyacademy.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.MyOrdersPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="[routerlink*='cart']")
	WebElement goToCart;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement myOrders;
	

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
		
		
	}
	
	public void waitForElementToDisappear() throws InterruptedException {
		Thread.sleep(1000);
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		 * wait.until(ExpectedConditions.invisibilityOf(ele));
		 */
	}
	
	public CartPage goToCartPage() {

		goToCart.click();
		return new CartPage(driver);
	}
	
	public MyOrdersPage goToOdersPage() {

		myOrders.click();
		return new MyOrdersPage(driver);
	}
	
	
}
