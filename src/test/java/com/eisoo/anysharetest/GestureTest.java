package com.eisoo.anysharetest;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;

public class GestureTest extends Functions  {
	  @BeforeClass
	  public void beforeClass() throws Exception {
		  try
		  {
			  init(); 
		  }
		  catch (Exception e)
		  {
			  e.printStackTrace();
			  System.out.println("init 失败！");
			  driver.quit();
			  init(); 
		  }
		  loginTest("116.236.224.243","zfy","123123");
		//  loginTest("anyshare.eisoo.com","zhu.fengyue@eisoo.com","zhufy0902##");
	  }
	  @AfterClass
	  public void afterClass() {
		  System.out.println("AfterClass");
		  driver.quit();
	  }
	  
	  @AfterMethod
	  public void afterMethod() throws Exception {
		  killAS();
	//	  killAS();   
	  }
	
	@Test
	public void h1_setOnTest() throws Exception
	{  startAS();
		setOn();
		driver.pressKeyCode(3);
		Thread.sleep(30000);
		driver.pressKeyCode(3);
		Thread.sleep(30000);
		startAS();
		assertTrue(exist("请您绘制手势密码"),"密码锁未开启");
		
		
	}
	@Test
	public void h2_unlockTest() throws Exception
	{
		startAS();
		startAS();
		assertTrue(exist("请您绘制手势密码"),"密码锁未开启");
		unlock();
		
	}
	@Test
	public void h2_modifyTest() throws Exception
	{
		modify();
	}
	@Test
	public void h3_setOffTest() throws Exception
	{
		setOff();
	}
	
	
	public void setOn() throws Exception
	{
		startAS();
		/*clickName("我的");
		clickName("手势密码锁定");*/
		enterSet("手势密码锁定");
		WebElement gesture=driver.findElementByClassName("android.widget.CheckBox");
			gesture.click();
			lockType("setOn");
	
	}

	
	
	public void unlock() throws Exception
	{
		startAS();
		startAS();
		if(exist("请您绘制手势密码"))
		{
			lockType("unlock");
		}
		Thread.sleep(1000);
	}
	

	public void setOff() throws Exception 
	{
		unlock();	//测试了解锁
		startAS();
		/*clickName("我的");
		clickName("手势密码锁定");*/
		enterSet("手势密码锁定");
		WebElement gesture=driver.findElementByClassName("android.widget.CheckBox");
		    gesture.click();
		    lockType("setOff");
	}
	

	public void modify() throws Exception 
	{
		unlock();
		startAS();
		/*clickName("我的");
		clickName("手势密码锁定");*/
		enterSet("手势密码锁定");
		clickName("修改密码锁");
		lockType("modify");
	}
	
	//
	public void lockType(String type) throws Exception
	{		
		List <WebElement> element=driver.findElementsById("com.eisoo.anyshare:id/gesture_container");		
		WebElement  gelemt=element.get(0);
		int startX=gelemt.getLocation().getX();
		int startY=gelemt.getLocation().getY();
		int stepX=gelemt.getSize().getWidth()/4;
		int stepY=gelemt.getSize().getHeight()/4;
//		System.out.println("startX:"+startX+"startY："+startY+"stepX："+stepX+"stepy："+stepY);
		int beginX=startX+stepX;
		int beginY=startY+stepY*2/3;
		TouchAction ta=new TouchAction(driver);
		Thread.sleep(1000);
/*		if(type=="modify") //修改
		{
			for(int i=0;i<=2;i++)
			{	ta.longPress(beginX,beginY).moveTo(stepX, 0).waitAction(1000).moveTo(stepX, 0).waitAction(1000).moveTo(0,stepY).waitAction(1000).moveTo(0,stepY).waitAction(1000).release().perform();
				Thread.sleep(1000);}
		ta.longPress(beginX,beginY).moveTo(stepX, 0).waitAction(1000).moveTo(stepX, 0).waitAction(1000).moveTo(0,stepY).waitAction(1000).moveTo(0,stepY).waitAction(1000).release().perform();
		Thread.sleep(1000); 
		ta.longPress(beginX,beginY).moveTo(stepX, 0).waitAction(1000).moveTo(stepX, 0).waitAction(1000).moveTo(0,stepY).waitAction(1000).moveTo(0,stepY).waitAction(1000).release().perform();
		Thread.sleep(1000);}
		
	if (type =="setOn")
		{
			ta.longPress(beginX,beginY).moveTo(stepX, 0).waitAction(1000).moveTo(stepX, 0).waitAction(1000).moveTo(0,stepY).waitAction(1000).moveTo(0,stepY).waitAction(1000).release().perform();
			Thread.sleep(1000);
			ta.longPress(beginX,beginY).moveTo(stepX, 0).waitAction(1000).moveTo(stepX, 0).waitAction(1000).moveTo(0,stepY).waitAction(1000).moveTo(0,stepY).waitAction(1000).release().perform();
			Thread.sleep(1000);
			
		}
		if(type=="unlock"||type=="setOff")
		{
			ta.longPress(beginX,beginY).moveTo(stepX, 0).waitAction(1000).moveTo(stepX, 0).waitAction(1000).moveTo(0,stepY).waitAction(1000).moveTo(0,stepY).waitAction(1000).release().perform();
			Thread.sleep(1000);
		}
*/
		
		switch(type)
		{	
			case "setOn":
				for(int i=0;i<=1;i++)
				{	ta.longPress(beginX,beginY).moveTo(stepX, 0).waitAction(1000).moveTo(stepX, 0).waitAction(1000).moveTo(0,stepY).waitAction(1000).moveTo(0,stepY).waitAction(1000).release().perform();
					Thread.sleep(1000);}
				break;
			case "modify":
				for(int i=0;i<=2;i++)
				{	ta.longPress(beginX,beginY).moveTo(stepX, 0).waitAction(1000).moveTo(stepX, 0).waitAction(1000).moveTo(0,stepY).waitAction(1000).moveTo(0,stepY).waitAction(1000).release().perform();
					Thread.sleep(1000);}
				break;
			case "unlock":
				 
			case "setOff":
				ta.longPress(beginX,beginY).moveTo(stepX, 0).waitAction(1000).moveTo(stepX, 0).waitAction(1000).moveTo(0,stepY).waitAction(1000).moveTo(0,stepY).waitAction(1000).release().perform();
				Thread.sleep(1000);
				break;
				
			default:
				System.out.println("非法参数！ ");
		
				
		}
		
	}
	



/*	  @BeforeMethod
	  public void beforeMethod() throws Exception {
		  startAS(); 
		  
	  }*/

}
