package rahulshettyacademy.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.sun.net.httpserver.Authenticator.Retry;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups = {"ErrorHandling"}, retryAnalyzer = rahulshettyacademy.TestComponents.Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException
	{		
		landingPage.LoginApplication("j8de8s@gmail.com", "Azim081000");
		Assert.assertEquals("Incorrect email o password.", landingPage.getErrorMessage());
	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException
	{
		String productName = "ZARA COAT 3";
		ProductCatalog productCatalog = landingPage.LoginApplication("anshika@gmail.com", "Iamking@000");
		productCatalog.addProductToCart(productName);
		Thread.sleep(2000);
		CartPage cartPage = productCatalog.goToCart();
		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
	 	Assert.assertFalse(match);
	 	
	}

}
