package rahulshettyacademy.Test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException {

		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		landingPage.loginApp("odwamzimba@gmail.com", "Luphawu2024");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErorMsg());

	}

	@Test
	public void ProductErrorValidation() throws InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCatalogue catalogue =  landingPage.loginApp("odwamzimba@gmail.com", "Luphawu2024");
		
		List<WebElement> products =  catalogue.getProductList();
		catalogue.addProductToCart(productName);
		CartPage cartPage = catalogue.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
	}

}
