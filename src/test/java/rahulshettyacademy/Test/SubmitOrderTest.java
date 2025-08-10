package rahulshettyacademy.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.MyOrdersPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
		
		
	
		// TODO Auto-generated method stub
		
		ProductCatalogue catalogue =  landingPage.loginApp(input.get("email"), input.get("password"));
		
		List<WebElement> products =  catalogue.getProductList();
		catalogue.addProductToCart(input.get("product"));
		CartPage cartPage = catalogue.goToCartPage();
		
		
		Boolean match =  cartPage.VerifyProductDisplay(input.get("product"));

		Assert.assertTrue(match);
		CheckOutPage checkOut = cartPage.goToCheckout();
		checkOut.SelectCountry("India");
		OrderPage orderPage = checkOut.submitOrder();
		String confirmMsg = orderPage.VerifyOrder();
		
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));

		
	}
	//verify ZARA COATE # is displaying in orders page
	@Test(dependsOnMethods={"submitOrder"},dataProvider="getData")
	public void OrderHistory(HashMap<String,String> input) {
		ProductCatalogue catalogue =  landingPage.loginApp(input.get("email"), input.get("password"));
		MyOrdersPage myOrders = landingPage.goToOdersPage(); 
		Assert.assertTrue(myOrders.VerifyOrdersDisplay(input.get("product")));
	}

	/*
	 * @DataProvider //This offeres the concept of parameterize your test cases with
	 * data public Object[][] getData() { HashMap<String,String> map = new
	 * HashMap<String,String> (); map.put("email","odwamzimba@gmail.com" );
	 * map.put("password", "Luphawu2024"); map.put("product", "ZARA COAT 3");
	 * 
	 * HashMap<String,String> map2 = new HashMap<String,String> ();
	 * map2.put("email","luphawu@gmail.com" ); map2.put("password", "Luphawu24");
	 * map2.put("product", "ADIDAS ORIGINAL"); return new Object[][] {{map},{map2}};
	 * //Object accepts any kind of data type }
	 */
	
	
	
	//Extent Reports
		//Used to receive results of our tests
		//Basic configurations of extent reports with Standard Test
	
	//With Json involved
	@DataProvider 
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data = getJsonDataToHashMap(System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}}; //Object accepts any kind of data type
	}
}



