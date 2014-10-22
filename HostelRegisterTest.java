package com.test.login.general.campus;


import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.browser.Browser;
import com.framework.util.DriverUtil;
import com.home.Home;
import com.home.HomeElement;
import com.login.Login;
import com.login.LoginElement;
import com.security.SecurityElement;
import com.test.testcycle.BaseTest;
import com.test.testcycle.TestConfiguration;
import com.visualizer.Visualizer;
import com.visualizer.VisualizerElement;

public class HostelRegisterTest extends BaseTest 
{

    /**
    * Check for security link at Login page.
    */
    
  
	@Test(groups={"all","sanity","smoke"})
	@Parameters(value = { "username", "password" })
	public void testValidLogin(String username, String password)
	{
		Browser.launchWebsite(botDriver, TestConfiguration.getTestURL());
		//Login with Valid credentials
		//Login.enterCredentials(botDriver, username, password);
		Login.enterCredentials(botDriver, username, password);
		Login.submitLoginForm(botDriver);
		
	}
	
	@Test(dependsOnMethods="testValidLogin",groups={"all","sanity","smoke"})
	public void testHostelRegister()
	{
		botDriver.hoverOnElement(botDriver.findElement(By.xpath(HomeElement.HOME_MENU_HOSTEL_XPATH_CAMPUS)));
		botDriver.waitUntilVisible(By.xpath(HomeElement.HOME_SUBMENU_HOSTEL_REGISTER_XPATH_CAMPUS));
		botDriver.clickOnElement(By.xpath(HomeElement.HOME_SUBMENU_HOSTEL_REGISTER_XPATH_CAMPUS));
		botDriver.clickOnElement(By.xpath(HomeElement.HOME_SUBMENU_HOSTEL_REGISTER_VISITOR_CHECKBOX_XPATH_CAMPUS));
		botDriver.findElement(By.id(HomeElement.HOSTEL_REGISTER_VISITOR_CHECKBOX_VISITORNAME_ID_CAMPUS)).sendKeys("testVisitor");
		
		botDriver.clickOnElement(By.id("ctl00_Body_txtReportedDateV"));
		
		//Home.DatePicker(botDriver,"19");
						
		Select dropdown = new Select(botDriver.findElement(By.id(HomeElement.HOSTEL_REGISTER_VISITOR_CITYNAME_ID_CAMPUS)));
		dropdown.selectByVisibleText("Vyara");
		
		botDriver.findElement(By.xpath(HomeElement.HOSTEL_REGISTER_VISITOR_ADDRESS_XPATH_CAMPUS)).sendKeys("testAddress");
		botDriver.clickOnElement(By.xpath(HomeElement.HOSTEL_REGISTER_SAVEBUTTON_XPATH_CAMPUS));
		
		boolean message = botDriver.waitUntilVisible(By.xpath("//p[contains(text(),'Record saved Successfully !!')]"));
		Assert.assertEquals(message, true ,"Hostel Register Data does NOT saved Sucessfully");
		
	}
	
		
}
