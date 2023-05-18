package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.AbstractComponents.AbstractComponent;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest{
	String productName = "ZARA COAT 3";
	@Test(dataProvider = "getData", groups = {"Purchase"})
	public void submitOrder(HashMap<String, String>input) throws IOException, InterruptedException
	{	
		ProductCatalog productCatalog = landingPage.LoginApplication(input.get("email"), input.get("password"));
		//List<WebElement>products = productCatalog.getProductList();
		productCatalog.addProductToCart(input.get("product"));
		Thread.sleep(2000);
		CartPage cartPage = productCatalog.goToCart();
		
		//Thread.sleep(2000);
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
	 	Assert.assertTrue(match);
	 	CheckOutPage checkOutPage = cartPage.goToCheckOut();
	 	
	 	checkOutPage.selectCountry("india");
	 	ConfirmationPage confirmationPage = checkOutPage.submitOrder();
	 	
	 	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
	 	String confirmMessage = confirmationPage.getConfirmationMessage();
	 	Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	 	System.out.println(confirmMessage);
	 	checkOutPage.signOut();
	}
	
	//@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest()
	{
		ProductCatalog productCatalog = landingPage.LoginApplication("j8de8s@gmail.com", "Azim0810");
		OrderPage ordersPage = productCatalog.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "anshika@gmail.com");
//		map.put("password", "Iamking@000");
//		map.put("product", "ADIDAS ORIGINAL");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "j8de8s@gmail.com");
//		map1.put("password", "Azim0810");
//		map1.put("product", "ZARA COAT 3");
		
		List<HashMap<String, String>>data =  getJsonDataToMap(System.getProperty("user.dir")+"/src/test/java/rahulshettyacademy/data/PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	
//	@DataProvider
//	public Object[][] getData()
//	{
//	return new Object[][] {{"j8de8s@gmail.com", "Azim0810", "ZARA COAT 3"}, 
//			       {"anshika@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"}};
//	}
}
