package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LearnerJourneyTest {

	public static void main(String[] args) throws InterruptedException {


		WebDriverManager.chromedriver().setup();
		
		WebDriver driver=new ChromeDriver();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		//Maximize Window
		driver.manage().window().maximize();
		
		//Navigate to url from config
		driver.get(ConfigReader.getProperty("baseUrl"));
		
		//Go to login page
		driver.findElement(By.cssSelector(".nav-link[data-test='nav-sign-in']")).click();
		
		//Register New Account
		wait.until(ExpectedConditions.elementToBeClickable(
		        By.cssSelector("a[data-test='register-link']")
		)).click();
		
		//String email = "user" + System.currentTimeMillis() + "@gmail.com";
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first_name"))).sendKeys("Rehan");
		
		driver.findElement(By.id("last_name")).sendKeys("Fazal");
		driver.findElement(By.id("dob")).sendKeys("2006-02-18");
		driver.findElement(By.id("address")).sendKeys("Phagwara");
		driver.findElement(By.id("postcode")).sendKeys("144401");
		driver.findElement(By.id("city")).sendKeys("Jalandhar");
		driver.findElement(By.id("state")).sendKeys("Punjab");
		
		//Select country from dropdown
		WebElement countryDropdown = driver.findElement(By.id("country"));
		Select select = new Select(countryDropdown);
		select.selectByVisibleText("India");
		
		driver.findElement(By.id("phone")).sendKeys("9142515485");
        driver.findElement(By.id("email")).sendKeys("rehan04@gmail.com");
        //Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("input[type='password']")
        )).sendKeys("Rehan@12345");
        
        //click on register button
        WebElement registerBtn = driver.findElement(By.cssSelector("[data-test='register-submit']"));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView(true);", registerBtn);

        //Thread.sleep(5000);
        js.executeScript("arguments[0].click();", registerBtn);
        
        
        
        // Login using registered credentials
     // Wait until login page loads
     // wait until Login heading appears
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h3[text()='Login']")
        ));
        
        // Now login
        driver.findElement(By.id("email")).sendKeys("rehan04@gmail.com");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("input[type='password']")
        )).sendKeys("Rehan@12345");

        driver.findElement(By.cssSelector("input[value='Login']")).click();
        
     
    
     // Search course
        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='search']"))
        );
        
        searchBox.sendKeys("hammer");

        searchBox.submit();

     // Open course detail page
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Hammer"))).click();

        // Add to basket
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Add to cart')]")
        )).click();
        
     // Assert basket count
        WebElement cartCount = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart-count"))
        );

        int count = Integer.parseInt(cartCount.getText());

        Assert.assertTrue(count > 0, "Cart count did not increase");

        System.out.println("Test Passed: Cart count increased successfully");

        driver.quit();
	}

}
