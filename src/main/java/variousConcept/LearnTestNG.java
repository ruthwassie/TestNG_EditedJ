package variousConcept;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LearnTestNG {
	
	WebDriver driver; 
	//By class Element List 
	By USER_NAME_FIELD = By.xpath("//*[@id=\"username\"]");  
	By PASSWORD_FIELD = By.xpath("//*[@id=\"password\"]"); 
	By LOGIN_BUTTON_FIELD = By.xpath("/html/body/div/div/div/form/div[3]/button");
	By DASHBOARD_HEADER_FIELD = By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div/h2");
	
	//create our test data :- we keep our test data separate
	String userName = "demo@techfios.com";
	String password = "abc123"; 
	String dashboardHeader = "Dashboard";
	
	
	@BeforeMethod //since it is testNG we do the @Beforemethod, then import
	public void init() {
	
	System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.manage().window().maximize(); 
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.manage().deleteAllCookies();
	driver.get("https://techfios.com/billing/?ng=login/");
	
	}
	@Test //choose "add testN library", then import
	public void loginTestNG() {
		
		//TestNG requires to have a plug in so in order to get the plug in, Help----Eclipse Marketplace----on the search box write testng
		//-----then choose TestNG for Eclipse ---- click install----confirm----accept the terms -----finish ----there is a box coming and we
		//chose select All------click Trust selected -----Restart----save-, then will restart
		
		//Now here we call them one by one
		
		driver.findElement(USER_NAME_FIELD).sendKeys(userName);
		driver.findElement(PASSWORD_FIELD).sendKeys(password);
		driver.findElement(LOGIN_BUTTON_FIELD).click();//now next we do validation, means assertion 
		
		//Assertion comes next to this and the one we choose is assertEquals(String actual, String expected, String message);void
		
		Assert.assertEquals(driver.findElement(DASHBOARD_HEADER_FIELD).getText(),dashboardHeader,"Dashboard is not available");
		
		
		//To email the report so what we do is we go in two test_output folder from the same paoject, 
		//then  right click on emailable-report.html---open with ----webbrowser ,,,,see the result
		
		//To share(email) the report, right click on the emailable-report.html...properties----on the report file---
		//right click---share or open with, then send with whatever the have 
		
		
	}
	@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
	}
	

}
