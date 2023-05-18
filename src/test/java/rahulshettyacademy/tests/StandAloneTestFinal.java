package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class StandAloneTestFinal {

	public static void main(String[] args) throws InterruptedException {
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("j8de8s@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Azim0810");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(product ->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@routerlink='/dashboard/cart']"))).click();
		//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));
		List<WebElement>cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	 	Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equals(productName));
	 	Assert.assertTrue(match);
	 	driver.findElement(By.xpath("//*[@class='totalRow']/button")).click();
	 	
	 	Actions a = new Actions(driver);
	 	a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
	 	
	 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class*='ta-results']")));
	 	
	 	driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
	 	driver.findElement(By.xpath("//a[contains(@class,'btnn')]")).click();
	 	
	 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
	 	String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	 	Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	 	System.out.println(confirmMessage);
	 	//driver.close();
	}

}
