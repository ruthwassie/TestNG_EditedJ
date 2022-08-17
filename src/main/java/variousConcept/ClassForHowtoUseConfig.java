package variousConcept;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ClassForHowtoUseConfig {
	
	WebDriver driver; 
	String browser;
	//String url; 
	
	//By class Element List 
			By USER_NAME_FIELD = By.xpath("//input[@id='username']");  
			By PASSWORD_FIELD = By.xpath("//*[@id=\"password\"]"); 
			By LOGIN_BUTTON_FIELD = By.xpath("/html/body/div/div/div/form/div[3]/button");
			By DASHBOARD_HEADER_FIELD = By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div/h2");
			
			//create our test data :- we keep our test data separate
			String userName = "demo@techfios.com";
			String password = "abc123"; 
			String dashboardHeader = "Dashboard";
			
			@BeforeClass  
			public void readConfig() {
				
				//our intention is calling the method form config property class
				//Four classes that java offer us
				//1. InputStream 2. BufferedReader 3. scanner 4. FileReader
				
				//how do we read File(how the FileReader happen).....//Through try and catch 
				
				try {									
					//we copy the below path for the argument from config.properties class going to properties and copy the path. we can save it as a variable and pass it or just copy and paste it from the class itself.
					InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties"); //new FileInputStream has a red line under, when we do the mouse, we choose the first one i.e. "Add argument to match 'FileInputStream(File)'
					Properties prop = new Properties();
					prop.load(input);//we chose the first one with InputStream inStream. Now java understand the value
					//prop.getProperty("browser"); //return as a string chrome. so we will save it in String variable see under 
					browser = prop.getProperty("browser");//will access the local one which is at line 13.   
					//String url = prop.getProperty("url");// so change this code to variable 
					//url = prop.getProperty("https://techfios.com/billing/?ng=admin/"); 
					
				}catch(Exception e) {
					e.printStackTrace();
					
				}
				
				
			}
			@BeforeMethod 
			public void init() {
				if(browser.equalsIgnoreCase("Chrome")) {
					System.setProperty("webdriver.chrome.driver","drivers\\chromedriver.exe" );
					driver = new ChromeDriver(); 
					
				}else if (browser.equalsIgnoreCase("Firefox")){
					System.setProperty("webdriver.gecko.driver","drivers\\geckodriver.exe"); 
					driver = new FirefoxDriver(); 
				}
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); 
				driver.manage().deleteAllCookies();
				driver.get("https://www.techfios.com/billing/?ng=admin/");
				
			}
			@Test
			public void loginTest() {
				driver.findElement(USER_NAME_FIELD).sendKeys(userName);
				driver.findElement(PASSWORD_FIELD).sendKeys(password);
				driver.findElement(LOGIN_BUTTON_FIELD).click();
				Assert.assertEquals(driver.findElement(DASHBOARD_HEADER_FIELD).getText(),dashboardHeader,"Dashboard is not available");
			}
			//@AfterMethod 
			public void tearDown() {
				driver.close();
				driver.quit();
			}
			
			

}
