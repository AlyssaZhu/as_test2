package com.eisoo.anysharetest;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;

public class Stressaest extends Functions
{
	
	  @AfterMethod
	  public void afterMethod() throws Exception {
		  killAS();
		  killAS();   
	  }

	  @BeforeClass
	  public void beforeClass() throws Exception {
		  System.out.println("BeforeClass");
		  init();
		  assertTrue(loginTest("116.236.224.243","zfy","123123"),"登录失败！");
	  }

	  @AfterClass
	  public void afterClass() {
		  System.out.println("AfterClass");
		  driver.quit();
	  }
	  
	//思路：找到取消
//	@Test
  public void Messages() throws Exception {
	  startAS();
	  clickName("我的");
	  for (int i=0;i<40;i++)
	  {
		  clickFile("我的消息");
		  startAS();
		  driver.findElementByAndroidUIAutomator("new UiSelector().text(\"zfy1给您共享了文档\").instance(0)").click();//Fail
		  //  clickFile("xiaomi3 ggg.ppt"); Fail
/*		  WebElement e1= driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.eisoo.anyshare:id/tv_file_send\").instance(0)");
		  e1.click();*/
		  // driver.findElementByAndroidUIAutomator("New UiSelector().desc(\"file\").instance(2)").click();
		  for (int j=0;j<5;j++)
			  {driver.pressKeyCode(4);
			  Thread.sleep(1000);
			  if(exist("常用"))
			  {
				  break;
			  }				  
			  }	  
		  System.out.println("第"+i+"次打开共享消息");
	//	  System.out.println(AndroidMonitor.getMemory("com.eisoo.anyshare"));
	  }	  
  }
	@Test(enabled=false)
	public void backupAlbum() throws Exception
	{
		startAS();
		clickName("传输");
		clickName("上传列表");
		if(!exist("自动备份任务"))
		{	/*clickName("我的");
			clickName("备份设置");*/
			enterSet("备份设置");
			WebElement backupBt = driver.findElementByClassName("android.widget.CheckBox");
		//	assertTrue(!backupBt.isEnabled(),"备份按钮开启了备份任务却未产生");
			backupBt.click();
			clickName("确定");
			driver.pressKeyCode(4);
			clickName("传输");
		}	
		  for (int i=0;i<40;i++)
		  {
			  driver.findElementById("com.eisoo.anyshare:id/tv_backup_title").click();
			  Thread.sleep(1000);
			  assertTrue(exist("我的相册"),"没有进入备份相册");
			  driver.pressKeyCode(4);
			  System.out.println("第"+(i+1)+"次进入我的相册");
		  }	
	}
	
	@Test(enabled=false)
	public void clearCaches() throws Exception
	{
		startAS();
		/*clickName("我的");
		clickName("清除缓存");*/
		enterSet("清除缓存");
		clickName("确定");
		startAS();//不重新进入，出现找不到元素的问题
		assertTrue(exist("当前缓存0B"),"未清除！！！");	
	}
	@Test
	public void setGesture() throws Exception
	{
		startAS();
		startAS();
		clickName("我的");
		clickName("手势密码锁定");
		WebElement gesture=driver.findElementByClassName("android.widget.CheckBox");
	
			gesture.click();
			List <WebElement> element=driver.findElementsById("com.eisoo.anyshare:id/gesture_container");		
			WebElement  gelemt=element.get(0);
			int startX=gelemt.getLocation().getX();
			int startY=gelemt.getLocation().getY();
			int stepX=gelemt.getSize().getWidth()/3;
			int stepY=gelemt.getSize().getHeight()/3;
			System.out.println("startX:"+startX+"startY："+startY+"stepX："+stepX+"stepy："+stepY);
			int beginX=startX+stepX/2;
			int beginY=startY+stepY/2;
			TouchAction ta=new TouchAction(driver);
			Thread.sleep(1000);
			ta.longPress(beginX,beginY).moveTo(stepX, 0).waitAction(1000).moveTo(stepX, 0).waitAction(1000).moveTo(0,stepY).waitAction(1000).moveTo(0,stepY+100).release().perform();
			Thread.sleep(1000);
			ta.longPress(beginX,beginY).moveTo(stepX, 0).waitAction(1000).moveTo(stepX, 0).waitAction(1000).moveTo(0,stepY).waitAction(1000).moveTo(0,stepY+100).release().perform();
			Thread.sleep(1000);
/*			ta.longPress(250, 714).moveTo(540,714).waitAction(1000).moveTo(810,714).waitAction(1000).moveTo(810,1004).waitAction(1000).moveTo(810,1294).waitAction(1000).release().perform();
			Thread.sleep(1000);
			ta.longPress(250, 714).moveTo(540,714).waitAction(1000).moveTo(810,714).waitAction(1000).moveTo(810,1004).waitAction(1000).moveTo(810,1294).waitAction(1000).release().perform();
			Thread.sleep(1000);*/
	/*		
			ta.longPress(250, 714).waitAction(1000).moveTo(290,0).waitAction(1000).moveTo(290,0).waitAction(1000).moveTo(0,290).waitAction(1000).moveTo(810,290).waitAction(1000).release().perform();
			Thread.sleep(1000);
			ta.longPress(250, 714).waitAction(1000).moveTo(290,0).waitAction(1000).moveTo(290,0).waitAction(1000).moveTo(0,290).waitAction(1000).moveTo(810,290).waitAction(1000).release().perform();
			Thread.sleep(1000);*/
			//重新开始
			
			/*WebElement  gelemt1=element.get(1);
			int startX1=gelemt1.getLocation().getX();
			int startY1=gelemt1.getLocation().getY();
			System.out.println("startX1:"+startX1+"startY1："+startY1);
			
			WebElement  gelemt3=element.get(1);
			int startX3=gelemt3.getLocation().getX();
			int startY3=gelemt3.getLocation().getY();
			System.out.println("startX3:"+startX3+"startY3："+startY3);*/
			
			/*WebElement e0=(WebElement) driver.findElementsByClassName("android.widget.ImageView").get(0);
			WebElement e1=(WebElement) driver.findElementsByClassName("android.widget.ImageView").get(1);
			WebElement e3=(WebElement) driver.findElementsByClassName("android.widget.ImageView").get(3);
			System.out.println(e0.getLocation().getX()+","+e0.getLocation().getY());
			System.out.println(e1.getLocation().getX()+","+e1.getLocation().getY());
			System.out.println(e3.getLocation().getX()+","+e1.getLocation().getY());*/
			
	}
	
	@Test(enabled=false)
	public void unlock() throws Exception
	{
		startAS();
		startAS();//刷新一遍才能识别
		if(exist("请您绘制手势密码"))
		{
			List <WebElement> element=driver.findElementsById("com.eisoo.anyshare:id/gesture_container");		
			WebElement  gelemt=element.get(0);
			int startX=gelemt.getLocation().getX();
			int startY=gelemt.getLocation().getY();
			int stepX=gelemt.getSize().getWidth()/3;
			int stepY=gelemt.getSize().getHeight()/3;
			System.out.println("startX:"+startX+"startY"+startY+"stepX"+stepX+"stepy"+stepY);
			int beginX=startX+stepX/2;
			int beginY=startY+stepY/2;
			TouchAction ta=new TouchAction(driver);
			Thread.sleep(1000);
			ta.longPress(beginX,beginY).moveTo(stepX, 0).waitAction(1000).moveTo(stepX, 0).waitAction(1000).moveTo(0,stepY).waitAction(1000).moveTo(0,stepY).waitAction(1000).release().perform();	
		}
		Thread.sleep(3000);
	//	assertTrue(!exist("请您绘制手势密码"),"没有解锁成功！");
	}
	
	@Test(enabled=false)
	public void setoffGesture() throws Exception 
	{
		unlock();	
		startAS();
		clickName("我的");
		clickName("手势密码锁定");
		WebElement gesture=driver.findElementByClassName("android.widget.CheckBox");
		    gesture.click();
		    List <WebElement> element=driver.findElementsById("com.eisoo.anyshare:id/gesture_container");		
			WebElement  gelemt=element.get(0);
			int startX=gelemt.getLocation().getX();
			int startY=gelemt.getLocation().getY();
			int stepX=gelemt.getSize().getWidth()/3;
			int stepY=gelemt.getSize().getHeight()/3;
			System.out.println("startX:"+startX+"startY"+startY+"stepX"+stepX+"stepy"+stepY);
			int beginX=startX+stepX/2;
			int beginY=startY+stepY/2;
			TouchAction ta=new TouchAction(driver);
			Thread.sleep(1000);
			ta.longPress(beginX,beginY).moveTo(stepX, 0).waitAction(1000).moveTo(stepX, 0).waitAction(1000).moveTo(0,stepY).waitAction(1000).moveTo(0,stepY).waitAction(1000).release().perform();
			Thread.sleep(1000);
			//	ta.longPress(beginX,beginY).moveTo(0, stepY).waitAction(1000).moveTo(0, stepY).waitAction(1000).moveTo(stepX,0).waitAction(1000).moveTo(stepX,0).release().perform();		
	}
	
//	@Test
	public void zmodifyGesture() throws Exception 
	{
			
		startAS();
		unlock();
		startAS();
		clickName("我的");
		clickName("手势密码锁定");
		/*WebElement gesture=driver.findElementByClassName("android.widget.CheckBox");
		    gesture.click();*/
		clickName("修改密码锁");
		    List <WebElement> element=driver.findElementsById("com.eisoo.anyshare:id/gesture_container");		
			WebElement  gelemt=element.get(0);
			int startX=gelemt.getLocation().getX();
			int startY=gelemt.getLocation().getY();
			int stepX=gelemt.getSize().getWidth()/3;
			int stepY=gelemt.getSize().getHeight()/3;
			System.out.println("startX:"+startX+"startY"+startY+"stepX"+stepX+"stepy"+stepY);
			int beginX=startX+stepX/2;
			int beginY=startY+stepY/2;
			TouchAction ta=new TouchAction(driver);
			Thread.sleep(1000);
			ta.longPress(beginX,beginY).moveTo(stepX, 0).waitAction(1000).moveTo(stepX, 0).waitAction(1000).moveTo(0,stepY).waitAction(1000).moveTo(0,stepY).waitAction(1000).release().perform();
			Thread.sleep(1000);
			ta.longPress(beginX,beginY).moveTo(0, stepY).waitAction(1000).moveTo(0, stepY).waitAction(1000).moveTo(stepX,0).waitAction(1000).moveTo(stepX,0).waitAction(1000).release().perform();	
			Thread.sleep(1000);
	}
	
	

  
 
  

	}

	