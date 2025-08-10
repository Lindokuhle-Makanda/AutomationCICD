package rahulshettyacademy.StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImplementation extends BaseTest {
	public LandingPage landingPage;
	public ProductCatalogue catalogue;
	public OrderPage orderPage;
	
	
	@Given("I landed on Ecommerce Page")
	public void i_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username <.+> and password <.+>$")
	public void logged_useraname_passweord(String username, String password) {
		
		catalogue = landingPage.loginApp(username,password);
	}
	
	@When("^When I add product <.+> to Cart$")
	public void i_add_product_to_Cart(String productName) throws InterruptedException{
		List<WebElement> products =  catalogue.getProductList();
		catalogue.addProductToCart(productName);
	}
	
	@When("^Checkout <.+> and submit the order$")
	public void checkout_submit_order(String productName) {
		CartPage cartPage = catalogue.goToCartPage();
		
		
		Boolean match =  cartPage.VerifyProductDisplay(productName);

		Assert.assertTrue(match);
		CheckOutPage checkOut = cartPage.goToCheckout();
		checkOut.SelectCountry("India");
		orderPage = checkOut.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string) {
		String confirmMsg = orderPage.VerifyOrder();
		
		Assert.assertTrue(confirmMsg.equalsIgnoreCase(string));

	}
}

