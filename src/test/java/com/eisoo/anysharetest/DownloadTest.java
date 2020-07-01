package com.eisoo.anysharetest;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DownloadTest extends Functions
{

  @BeforeClass
  public void beforeClass() throws Exception {
	  try
	  {  init();   }
	  catch (Exception e)
	  {
		  System.out.println("init 失败！");
		//  driver.quit();
		  init(); 
	  }
	  loginTest("116.236.224.243","zfy","123123");
  }

  @AfterClass
  public void afterClass()  {
	  driver.quit();
  }
  
  @AfterMethod
  public void afterMethod() throws Exception {
	  System.out.println("afterMethod");
	  killAS();
  }
  
	//普通下载 开启仅在Wi-Fi&&WiFi环境
	@Test(enabled=true) 
	public void  downldAFile() throws Exception
	{
		System.out.println("Begin************"+Thread.currentThread().getStackTrace()[1].getMethodName());
	//	switchWifi(1);
		assertTrue(download(1,1,1),"下载失败！");
		assertTrue(exist("下载完成(1)",15),"下载失败！");
	}
		
	@Test(enabled=true) 
	public void downldManyFile() throws Exception
	{
		System.out.println("Begin************"+Thread.currentThread().getStackTrace()[1].getMethodName());	
		switchWifi(1);
		startAS();
		assertTrue(download(10,1,1),"下载失败！");
	}
	
	
	@Test(enabled=true) 
	public void downldMultiFile() throws Exception
	{
		startAS();
		assertTrue(download(6,1,1),"下载失败！");
		clearCache();
	}

	@Test(enabled=true) 
	public void downldAllFile() throws Exception
	{
		System.out.println("Begin************"+Thread.currentThread().getStackTrace()[1].getMethodName());
		startAS();
		assertTrue(download(30,1,1),"下载失败！");
		clearCache();
	//	killAS();
	}
	
	@Test(enabled=true,priority=1) //case:开启仅在Wi-Fi&&WiFi-> 切换为4G的环境（选“继续传输”）
	public void downldNoWifi() throws Exception
	{
		System.out.println("Begin************"+Thread.currentThread().getStackTrace()[1].getMethodName());
		switchWifi(1);
		switchWifi(0);
		startAS();
		assertTrue(download(1,0,1),"下载失败！");
		assertTrue(!exist("等待Wi-Fi..."),"存在等待Wi-Fi标记");
		clearCache();
		switchWifi(1);
	}
	
	//之前在应用外尝试切网，发现没有反应，因此调整切换Wi-Fi的方法顺序
	//case:开启仅在Wi-Fi&&WiFi-> 切换为4G的环境（选“仅在Wi-Fi”）
	@Test(enabled=true,priority=2)  
	public void downldWaitWfi() throws Exception
	{
		System.out.println("Begin************"+Thread.currentThread().getStackTrace()[1].getMethodName());
		startAS();
		switchWifi(1);
		Thread.sleep(2000);
		switchWifi(0);
		assertTrue(download(2,1,1),"下载失败！");
		assertTrue(exist("等待Wi-Fi..."),"不存在等待Wi-Fi标记");
		clearCache();
		switchWifi(1);
	}
	
	//普通下载 开启仅在Wi-Fi&&WiFi环境
	@Test(enabled=true,priority=2) 
	public void  downldNoPermisison() throws Exception
	{
		System.out.println("Begin************"+Thread.currentThread().getStackTrace()[1].getMethodName());
		startAS();
		assertTrue(!download(1,1,0),"没有权限的文件能够下载成功！");
		clearCache();
	}


}
