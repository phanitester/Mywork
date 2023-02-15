package nopcommerce_TestCases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import freemarker.log.Logger;
import net.bytebuddy.implementation.FieldAccessor.PropertyConfigurable;


public class BaseClass 
{
public static WebDriver driver;
public String url = "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F";
public static Logger logger;

@Parameters("browser")
@BeforeClass
public void Start_Browser(String br)
{
	if(br.equals("chrome"))
	{
		System.setProperty("webdriver.chrome.driver","G:\\Eclipse Practise\\project_1\\Drivers\\chromedriver.exe");
		
		driver= new ChromeDriver();
	}
	else if(br.equals("firefox"))
	{
		System.setProperty("webdriver.chrome.driver","G:\\Eclipse Practise\\project_1\\Drivers\\geckodriver.exe");
		driver= new FirefoxDriver();
	}
	else if(br.equals("edge"))
	{
		System.setProperty("webdriver.chrome.driver","G:\\Eclipse Practise\\project_1\\Drivers\\msedgedriver.exe");
		driver= new EdgeDriver();
	}
	logger = Logger.getLogger("NOPCOMMERCE");
	PropertyConfigurator.configure("log4j.properties");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.manage().deleteAllCookies();
}

@AfterClass
public void tear_down()
{
	
	driver.quit();
}


public String captureScreen(String tname) throws IOException {

	
	String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			
	TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
	String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

	try {
		FileUtils.copyFile(source, new File(destination));
	} catch (Exception e) {
		e.getMessage();
	}
	return destination;

}



}
