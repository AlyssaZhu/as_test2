package com.eisoo.anysharetest;

import org.testng.annotations.Test;

//import android.os.Build;

import io.appium.java_client.NetworkConnectionSetting;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Demoxest extends Functions {

		  
				   @Test
				   public void m_shareQQ() throws Exception
				   {
				 	  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
				 	  enterDest("共享");
				 	  manageFile(0,"外链分享");
				 	  assertTrue(manExlink("QQ"),"未完成QQ分享");  
				   }
				   
				  
				   @Test
					  public void n4_share() throws Exception {
						  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
						  startAS();
						  switchAccount("zfy","123123");
						  enterDest("共享");  
						  manageFile(1,"内链共享");
						  WebElement e1= driver.findElementByName("保存");
						  assertTrue(!e1.isEnabled(),"当前保存按钮为可用状态，未编辑应该不可用！");
						  if(!nameExist("王十一"))
						  { addVisitor();
						  	}
						  else 
							  driver.pressKeyCode(4);
						 Thread.sleep(2000);
						 manageFile(1,"内链共享");
						 assertTrue(nameExist("王十一"),"不存在此用户名");   
						 delVisitor();
						 Thread.sleep(2000);
						 manageFile(1,"内链共享");
						 assertTrue(!nameExist("王十一"),"存在此用户名"); 
					  }
	//========================================================/
	  public boolean compareDate(String date1,String date2) throws Exception
	  {
		  DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		  Date dt1= df.parse(date1);
		  Date dt2=  df.parse(date2);
		  /*Date dt1= new Date(date1);
		  Date dt2=  new Date(date2);*/
		  System.out.println(dt1);
		  System.out.println(dt2);
		  System.out.println(dt1.getTime());
		  System.out.println(dt2.getTime());
		  if(dt1.getTime()>=dt2.getTime())
		  {	return true;}
		  else
			 return false;
	  }
//	  @Test
	  public void compare() throws Exception
	  {
		 System.out.println(compareDate("2018/05/18 15:05","2018/05/11 16:13"));

	  }
	
public static String deviceName="default";
	
	public void getInfo() throws Exception
	{	Thread.sleep(3000);
		driver.pressKeyCode(3);
		clickName("设置");
		clickFile("关于手机");
		Thread.sleep(3000);
		if(exist("HUAWEI P9"))
		{
			deviceName="P9";
		}
		else if(exist("HUAWEI P10"))
		{
			deviceName="P10";
		}	
		
		driver.pressKeyCode(4);
		driver.pressKeyCode(4);
	}
	@Test
	public void printDevice()
	{
		System.out.println(deviceName);
	}
	  

/*  @Test
	public void switchWifi( ) throws Exception
	{	
		System.out.println(driver.getNetworkConnection().value);
		Thread.sleep(3000);
		driver.setNetworkConnection(new NetworkConnectionSetting(1));
	  System.setProperty("webdriver.firefox.bin", "	/Applications/Firefox.app/Contents/MacOS/firefox");
	  dr=new FirefoxDriver();
	  Thread.sleep(3000);
	  dr.get("https://www.baidu.com");*/
  
	/* 
	 * 暂停
	 *  @Test
	  public void pauseAll() throws Exception
	  {
		  enterDest("上传");
		  assertTrue(upload(10,1,"上传音频"),"上传失败") ; 
		  assertTrue(exist("全部暂停"),"不存在全部暂停按钮");
		  clickName("全部暂停");
		  assertTrue(!exist("正在上传..."),"存在正在上传");  
		  clickName("全部开始");
		  assertTrue(exist("正在上传..."),"不存在正在上传"); 
		  assertTrue(!exist("已暂停"),"存在已暂停"); 
		  clearUploadList(); 
	  }
	  
	  public void startAll() throws Exception
	  {
		  enterDest("上传");
		  assertTrue(upload(10,1,"上传音频"),"上传失败") ; 
		  assertTrue(exist("全部暂停"),"不存在全部暂停按钮");
		  clickName("全部暂停");
		  assertTrue(!exist("已暂停"),"存在已暂停");  
	  }
 */
	@BeforeMethod
	  public void beforeMethod() throws Exception {
		  startAS();
	  }

	  @AfterMethod
	  public void afterMethod() throws Exception {
		  killAS(); }
	  @BeforeClass
	  public void beforeClass() throws Exception  {
		  try
		  {	  init(); 
		  }
		  catch (Exception e)
		  {
			  System.out.println("init 失败！");
			//  driver.quit();
			  init(); 
		  }
		  loginTest("116.236.224.243","zfy","123123");
	  }

	  @AfterClass
	  public void afterClass() {
		  driver.quit();
	  }

}
