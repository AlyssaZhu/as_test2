package com.eisoo.anysharetest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.Date;

import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;

public class sortTest extends Functions{
	@Test
  public void timeOrder() throws Exception {
	  enterDest("根目录");
	  changeMode("按时间倒序排序"); //获取页面时间标签，逐个比较
	 for(int i=0;i<5;i++)
	 {	WebElement e1=(WebElement) driver.findElementsById("com.eisoo.anyshare:id/tv_file_time").get(i);
	 	WebElement e2=(WebElement) driver.findElementsById("com.eisoo.anyshare:id/tv_file_time").get(i+1);
	 	System.out.println("比较第"+i+"次！");
	 	assertTrue(compareDate(e1.getText(),e2.getText()),"时间顺序有误！");
	 }
		changeMode("按文件名称排序");
  }

  @Test
  public void thumbnail_View() throws Exception
  {
	 enterDest("根目录");
	 changeMode("缩略图视图");
	 startAS();
	 assertTrue(viewMode(),"非缩略图视图模式");
	 changeMode("列表视图");
	 assertTrue(!viewMode(),"非列表视图模式");
  }
  
  //可以判断第一个第2个是否坐标相等
  public boolean viewMode()
  {		WebElement e1= (WebElement) driver.findElementsById("com.eisoo.anyshare:id/ll_content").get(0);
		WebElement e2= (WebElement) driver.findElementsById("com.eisoo.anyshare:id/ll_content").get(1);
	if(e1.getLocation().getY()==e2.getLocation().getY()) 
	{	System.out.println("高度是："+e1.getLocation().getY());
		return true;}
	else 
		return false;
  }
  
/*  public void clickTitle()
  {
	  clickId("com.eisoo.anyshare:id/tv_title"); 
  }
  public void changeMode(String type) throws Exception
  {
	  clickId("com.eisoo.anyshare:id/tv_title");
	  clickName(type);
  }*/
  
 
  
  @BeforeClass
  public void login() throws Exception {
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
  

  @BeforeMethod
  public void beforeMethod() throws Exception {
	  startAS();
  }

  @AfterMethod
  public void afterMethod() throws Exception {
	  killAS();
  }
  
  @AfterClass
  public void afterClass()  {
	  driver.quit();
  }

}
