package rahulshettyacademy.pageobjects;

import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	WebDriver driver;
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement>productNamesInTableEle;
	
	By productsBy = By.cssSelector("tr td:nth-child(3)");
	
	public Boolean verifyOrderDisplay(String productName)
	{	
		waitForElementToAppear(productsBy);
		Boolean match = productNamesInTableEle.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
}
