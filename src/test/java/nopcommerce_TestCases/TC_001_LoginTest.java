package nopcommerce_TestCases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import nopcommerce_PageObjects.LoginPage;
import nopcommerce_Utilities.XLUtilities;

public class TC_001_LoginTest extends BaseClass
{
	
	@Test(dataProvider = "LoginData")
	public void Login_Test(String username, String password)  throws IOException
	{
		LoginPage lp = new LoginPage(driver);
		driver.get(url);
		logger.info("URL is opened");
		lp.credentials(username, password);
		logger.info("Entered Credentials");
		lp.btn_login();
		logger.info("Clicked on Login");
		
		if(driver.getTitle().equals("Dashboard / nopCommerce administration"))
		{
			Assert.assertTrue(true);
			logger.info("Title is Same, Successfully Logged In");
			lp.btn_logout();
		}
		else 
		{
		logger.info("Credential are wrong");
		captureScreen("Login_Test");	
		Assert.assertTrue(false);
		}
	}
	@DataProvider(name="LoginData")
	String [][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/src/test/java/TestData/Userdata.xlsx";
		
		int rownum=XLUtilities.getRowCount(path, "Sheet1");
		int colcount=XLUtilities.getCellCount(path,"Sheet1",1);
		
		String logindata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=XLUtilities.getCellData(path,"Sheet1", i,j);//1 0
			}
				
		}
	return logindata;
	}
}
