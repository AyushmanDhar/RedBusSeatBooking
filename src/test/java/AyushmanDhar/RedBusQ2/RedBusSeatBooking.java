package AyushmanDhar.RedBusQ2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RedBusSeatBooking{
	
	WebDriver driver;
	@BeforeMethod
	public void initializeDriver() {
		//WebDriverManager ChromeDriver setup
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		//Add implicit wait & maximize the window
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
	}
	@Test
	public void busSeatSelectionTest() {
		//launch url
		driver.get("https://www.redbus.in/");
		
		//source location
		driver.findElement(By.xpath("//label[text()='From']/preceding-sibling::input")).sendKeys("Kolkata");
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//text[text()='Esplanade']"))));
		driver.findElement(By.xpath("//text[text()='Esplanade']")).click();
		
		//destination location
		driver.findElement(By.xpath("//label[text()='To']/preceding-sibling::input")).sendKeys("Bakkhali");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//text[text()='Bakkhali']"))));
		driver.findElement(By.xpath("(//text[text()='Bakkhali'])[2]")).click();
		
		//click on date field
		driver.findElement(By.className("labelCalendarContainer")).click();
		
		/***note: to pick the date (custom) run the following element on loop until below code gives your desired MMM yyyy formatted string 
		  
		String[] x=driver.findElement(By.cssSelector("div[class*='DayNavigator__IconBlock']:nth-of-type(2)")).getText().split(" ");
		System.out.println(x[0]+" "+x[1].substring(0,x[1].length()-2));
		***/
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("svg[xmlns*='w3.org/2000/svg']"))));
		driver.findElement(By.cssSelector("svg[xmlns*='w3.org/2000/svg']")).click();
		
		//Select your desired date
		driver.findElement(By.xpath("//span[text()='18']")).click();
		
		//click on search
		driver.findElement(By.id("search_button")).click();
		
		/***click on view seats 
		note: here it picks the first option every time, can be customized using other elements
		***/
		driver.findElement(By.className("view-seats")).click();
		
		/***work on canvas:
		note: the DOM provides the width & height of the canvas, instead of relying on hard coded values below, we may replace 100 & -80 with 
		width/4 & height/2.5 (haven't tried) to int so proportions stay fine irrespective of any changed dimension in future
		***/
		Actions ac=new Actions(driver);
		ac.moveToElement(driver.findElement(By.tagName("canvas")),100,-80).click().build().perform();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@AfterMethod
	public void tearDown() {
		
		//Close the session
		driver.quit();
	}

}
