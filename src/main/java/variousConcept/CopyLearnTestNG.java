package variousConcept;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CopyLearnTestNG { 
			
		WebDriver driver;
		String browser;
		String url;
		
		// Element list
		By USER_NAME_FIELD = By.xpath("//input[@id='username']");
		By PASSWORD_FIELD = By.xpath("//*[@id=\"password\"]");
		By LOGIN_BUTTON_FIELD = By.xpath("/html/body/div/div/div/form/div[3]/button");
		By DASHBOARD_HEADER_FIELD = By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div/h2");
		By CUSTOMER_MENU_BUTTON_FIELD = By.xpath("//*[@id=\"side-menu\"]/li[3]/a/span[1]"); 
		By ADD_CUSTOMER_BUTTON_FIELD = By.xpath("//*[@id=\"side-menu\"]/li[3]/ul/li[1]/a");
		By FULL_NAME_FIELD =By.xpath("//*[@id=\"account\"]"); 
		By COMPANY_FIELD = By.xpath("//select[@id = \"cid\"]"); //always when you inspect do the boxes not on the name of the company or full name
		By EMAIL_FIELD = By.xpath("//input[@id = \"email\"]"); 

		// Test data
		String userName = "demo@techfios.com";
		String password = "abc123";
		String dashboarHeader = "Dashboard";
		//String addContact = "Add Contact"; 
		
		@BeforeClass
		public void readConfig() {
			
			//InputStream //BufferedReader //Scanner //FileReader
			
			try {
				
				InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");//copy the path from config.properties folder
				Properties prop = new Properties();
				prop.load(input);
				browser = prop.getProperty("browser");
				url = prop.getProperty("url");
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}

		@BeforeMethod
		public void init() {
			
			if(browser.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
				driver = new ChromeDriver();
			}else if (browser.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
			}

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
			driver.get(url);
		}

		//@Test //no need to test this because we called it on the addcustomer method below
		public void loginTest() {

			driver.findElement(USER_NAME_FIELD).sendKeys(userName);
			driver.findElement(PASSWORD_FIELD).sendKeys(password);
			driver.findElement(LOGIN_BUTTON_FIELD).click();
			Assert.assertEquals(driver.findElement(DASHBOARD_HEADER_FIELD).getText(), dashboarHeader,
					"Dashboard is not available.");

		}
		@Test
		public void addcustomer() throws InterruptedException {
			
			//first we have to call the loginTest method here, so first it will start from log in
			loginTest(); //then now going to customers and add customers button from the techfios billing login page.so
						//this loginTest will do the assertion part as well for the login
			
			driver.findElement(CUSTOMER_MENU_BUTTON_FIELD).click();
			driver.findElement(ADD_CUSTOMER_BUTTON_FIELD).click();
			
			//Assert.assertEquals(driver.findElements(ADD_CUSTOMER_BUTTON_FIELD), addContact, "Page not found"); figure it out by urself how to do :)
			
								//OR
			
			Thread.sleep(3000);
			boolean fullNameField = driver.findElement(FULL_NAME_FIELD).isDisplayed(); //the full name is unique element so we need assertion that 
			Assert.assertTrue(fullNameField, "Add Customer Page is not Available");//choose(boolean condition, String)
			
			//now after this we need to fill out the form.
			driver.findElement(FULL_NAME_FIELD).sendKeys("Rossa Paul");
			
//			now it is a drop down field so what we do is 
			Select sel = new Select(driver.findElement(COMPANY_FIELD));
			sel.selectByVisibleText("Techfios"); //we go back to company drop down list and choose and copy the name and past it in the argument 
			
		}
		//@AfterMethod 
		public void tearDown() {
			driver.close();
			driver.quit();
			
		}
}


