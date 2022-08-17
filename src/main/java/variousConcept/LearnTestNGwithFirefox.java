package variousConcept;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LearnTestNGwithFirefox {
	
	public class LearnTestNG {
		
		WebDriver driver; 
		String browser = "chrome"; //("Firefox");// This one is for if and else to test both chrome and firefox
		
		//By class Element List 
		By USER_NAME_FIELD = By.xpath("//*[@id=\"username\"]");  
		By PASSWORD_FIELD = By.xpath("//*[@id=\"password\"]"); 
		By LOGIN_BUTTON_FIELD = By.xpath("/html/body/div/div/div/form/div[3]/button");
		By DASHBOARD_HEADER_FIELD = By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div/h2");
		
		//create our test data :- we keep our test data separate
		String userName = "demo@techfios.com";
		String password = "abc123"; 
		String dashboardHeader = "Dashboard";
		
		
		@BeforeMethod 
		public void init() {
			
			//lets set up our system.setproperty. for Firefox we call it "geckodriver" and we need the driver to do firefox so
			//click geckodriver releases,select 0.31.0 latest, then download the zipfile --"geckodriver-v0.31.0-win64.zip", then copy the 
			//gecko driver and paste it on the same project driver file. 
													//OR
			//
			//Go to google and search "firefox 102 selenium webdriver download" then select/chose Install browser drivers - Selenium
			
			
			System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe"); 
			driver = new FirefoxDriver(); // these two codes are for fire fox
			
			driver.manage().window().maximize(); 
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
			driver.get("https://techfios.com/billing/?ng=login/");
			
			//Now if i want to perform both Firefox and Chrome, we use if and elseSystem.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe"); 
			
//			System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe"); 
//			driver = new ChromeDriver(); //this one is for Chrome .
//			
//			System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe"); 
//			driver = new FirefoxDriver(); // these two codes are for fire fox
								
//			if(browser.equalsIgnoreCase("Chrome")) {
//				System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe"); 
//				driver = new ChromeDriver(); 
//				
//			}else {
//				System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe"); 
//				driver = new FirefoxDriver(); 
//			}	
			
			//driver.manage().window().maximize(); 
		}
//			here also if we want to have a control on else(want the else to be printed) we do
		
//			if(browser.equalsIgnoreCase("Chrome")) {
//			System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe"); 
//			driver = new ChromeDriver(); 
//			
//		} else if(browser.equalsIgnoreCase("Firefox")) {
//			System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe"); 
//			driver = new FirefoxDriver(); 
//		}
		
		//The main idea of Build a Fram is not to touch(No manipulation of) our code, so With out touching the code, 
		//how we are managing or manipulating in the industry. See the config.propertiy file on the next file on the same project
		
		@Test
		public void loginTest() throws InterruptedException {
			
			driver.findElement(USER_NAME_FIELD).sendKeys(userName);
			driver.findElement(PASSWORD_FIELD).sendKeys(password);
			driver.findElement(LOGIN_BUTTON_FIELD).click();
			Assert.assertEquals(driver.findElement(DASHBOARD_HEADER_FIELD).getText(),dashboardHeader,"Dashboard is not available");
			
			
			//Thread.sleep(10000); //after this we  always have to emailable the report, so 
		}
		//@AfterMethod
		public void tearDown() {
			driver.close();
			driver.quit();
			//see the clean format, how to use and write in the classForHowtoUseConfig class on the config package. 
		}
		
	}
}
