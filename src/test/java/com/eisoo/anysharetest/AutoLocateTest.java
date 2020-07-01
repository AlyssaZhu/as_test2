package com.eisoo.anysharetest;

import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class AutoLocateTest extends Functions {
  @Test
  public void cancelLink() throws Exception {
	  copyLink();
	  startAS();
	  Thread.sleep(2000);
	  assertTrue(exist("查看"),"不存在定位弹框！");
	  clickName("取消");  
  }
  
  @Test
  public void openLink() throws Exception
  {
	  Thread.sleep(3000);
	  copyLink();
	  startAS();
	//  startAS();
	  Thread.sleep(2000);
	  assertTrue(exist("查看"),"不存在定位弹框！");
	  clickName("查看"); 
	  Thread.sleep(2000);
	  assertTrue(exist(driver.findElement(By.name("共享"))),"不存在此元素！");
  }
  
   @Test
  public void nolink() throws Exception
  {
	  enterDest("共享");
	  manageFile(0,"内链共享");
	  clickName("复制内链");
	  startAS();
	  assertTrue(!exist("查看"),"存在定位弹框！");
  }
  
 /* 该方法目前只针对P9有效，其它手机需要适配
  思路一：获取最后，内链的控件位置，复制/粘贴，获取复制,由于系统应用每个手机都不一样，可能不够通用   
  思路二：获取包含内链信息的目标元素，以及其父节点，计算复制的相对坐标*/
  
  public void copyLink() throws Exception{
	  driver.pressKeyCode(3);
	  driver.pressKeyCode(3);
	//  driver.launchApp();
	  clickDesc("信息");
	  clickName("‪185 5181 9591‬");
	  //第一个try  没实际意义，只是为了验证API
  try{
		  WebElement e1=driver.findElement(By.xpath("//android.widget.TextView[@text='123']/parent::*/parent::*/parent::*/parent::*"));
		  System.out.println(e1.getLocation().getY());
	  }
	  catch( Exception e)
	  {
		  System.out.println("error3!");
	  }
	  
  try{
		  WebElement e1=driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'AnyShare://vivi/')]"));
		  WebElement e2=driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'AnyShare://vivi/')]/../../../.."));
		  System.out.println(e2.getLocation().getY());
		  System.out.println(e1.getLocation().getX());
		  int x=e1.getLocation().getX()+20;
		  int y=e2.getLocation().getY(); 
		  int x0= e1.getLocation().getX()+e1.getSize().width/2;
		  int y0= e1.getLocation().getY()+e1.getSize().height/2;
		 driver.swipe(x0, y0, x0, y0, 2000); //长按信息中心位置，使弹出菜单选项
		 System.out.println("观察");
		 driver.tap(1, x, y, 200);
		 driver.pressKeyCode(4);
		 
	  }
	  catch( Exception e)
	  {
		  System.out.println("error4!");
	  }
	 
	  // WebElement e1=driver.findElementByAndroidUIAutomator("new UiSelector().resourceId("").getChild(new UiSelector().textStartWith("AnyShare:")))");
	//		  driver.findElementByAndroidUIAutomator("new UiSelector().description(\"file\").instance("+i+")").click();
  }
  

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
