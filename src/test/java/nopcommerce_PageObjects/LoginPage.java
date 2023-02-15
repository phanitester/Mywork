package nopcommerce_PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
	public WebDriver ldriver;
	
	// Create Constructor for web driver
	public LoginPage(WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath = "//input[@id='Email']")
	WebElement email;
	
	@FindBy(xpath = "//input[@id='Password']")
	WebElement pass;
	
	@FindBy(xpath = "//button[normalize-space()='Log in']")
	WebElement login;
	
	@FindBy(xpath = "//aside/a/img[1]")
	WebElement logo;
	
	@FindBy(xpath = "//a[normalize-space()='Logout']")
	WebElement logout;
	
	public void credentials(String user, String pwd)
	{
		email.clear();
		email.sendKeys(user);
		pass.clear();
		pass.sendKeys(pwd);
	}
	
	public void btn_login()
	{
		login.click();
	}
	
	public void btn_logout()
	{
		logout.click();
	}

	public boolean ch_logo() 
	{
		return logo.isDisplayed();
	}

}
