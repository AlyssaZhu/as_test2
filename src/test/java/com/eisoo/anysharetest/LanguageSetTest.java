package com.eisoo.anysharetest;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;


public class LanguageSetTest extends Functions {
	
	@BeforeClass
	public void login() throws Exception
	{
		init();
		assertTrue(loginTest("116.236.224.243","zfy","123123"),"登录失败！");
	}
	@AfterClass
	public void quit()
	{	
		driver.quit();
	}
	@AfterMethod
	public void sleep() throws Exception
	{
	//	Thread.sleep(2000);
		killAS();
	}
	
	@Test
	public void setbCh() throws Exception
	{
		startAS();
		startAS();
		langSet("简体中文");
	}
	@Test
	public void setaTraditional() throws Exception
	{
		startAS();
		startAS();
		langSet("繁體中文");
	}
	@Test
	public void setaEn() throws Exception
	{
		startAS();
		startAS();
		langSet("English");
	}
		
	@Test 
	public void setcChHome() throws Exception
	{
		startAS();
		startAS();
		langSetHome("简体中文");
		killAS();
	}
	@Test 
	public void setbTraditionalHome() throws Exception
	{
		startAS();
		startAS();
		langSetHome("繁體中文");
	}
	@Test 
	public void setbEnHome() throws Exception
	{
		startAS();
		startAS();
		langSetHome("English");
	}


	
	public String language()
	{
		try{
			if(exist(driver.findElementById("com.eisoo.anyshare:id/tv_languageSetting")))//是否在首页
			{
				if(exist("语言设置"))
					return "简体中文";
					else if(exist("語言設置"))
					return "繁體中文";
					else if (exist("Language Settings"))
					return "English";
					else	
						return "error";	
			}
			
		}
		
		catch(Exception e)
		{
			if(exist("传输"))
				return "简体中文";
				else if(exist("傳輸"))
				return "繁體中文";
				else if (exist("Transfer"))
				return "English";
				else	
					return "error";	
			
		}
		return null;
		
	}
	
	public boolean isSame(String lang) throws Exception
	{
		Thread.sleep(3000);
		startAS();//目的是为了刷新一下界面，
		String currentlang=language();
		System.out.println("当前APP界面上的语言*********"+currentlang);
		if(lang.equals(currentlang))
		{
			return true;
		}
		else 
			return false;
	}
	
	public void langSet(String lang) throws Exception
		{
			Thread.sleep(1000);
			clickId("com.eisoo.anyshare:id/rl_setting");
			clickId("com.eisoo.anyshare:id/rl_setting");
			driver.findElementById("com.eisoo.anyshare:id/rl_language_set").click();

		//	enterSet("语言设置");
			clickName(lang);
			WebElement e1=driver.findElementById("com.eisoo.anyshare:id/tv_language_set_save");
			if(e1.isEnabled())
			{	e1.click();
				assertTrue(isSame(lang),"语言没有切换!");}
			else 
				System.out.println("语言没作修改，没必要保存");	
	}
	
	public void  langSetHome(String lang) throws Exception
	{
		logout();
		clickId("com.eisoo.anyshare:id/tv_languageSetting");
		WebElement e1=driver.findElementById("com.eisoo.anyshare:id/tv_language_set_save");
		clickName(lang);
		if(e1.isEnabled())
		{	e1.click();
			assertTrue(isSame(lang),"语言没有切换!");}
		else 
			System.out.println("语言没作修改，没必要保存");	
	}
	
	public void logout() throws Exception
	{
		try{
			exist(driver.findElementById("com.eisoo.anyshare:id/tv_languageSetting"));
		
			System.out.println("已注销状态");
			}
	
		catch(Exception e)
		{
			clickId("com.eisoo.anyshare:id/rl_setting");
			swipe("down");
			clickId("com.eisoo.anyshare:id/tv_outapp");
			clickId("com.eisoo.anyshare:id/positiveButton");
			Thread.sleep(1000);
		}
	}
	
	
}
