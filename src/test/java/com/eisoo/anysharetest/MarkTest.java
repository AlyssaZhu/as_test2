package com.eisoo.anysharetest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;

public class MarkTest extends Functions{
	
	//构思： 判断是否收藏, 找到未收藏文件，进行收藏。
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
			//  driver.quit();
			//  init(); 
		  }
		 
		  loginTest("116.236.224.243","zfy","123123");
	  }

	
	@Test
	public void markAnyFile() throws Exception
	{
		enterDest("评论");
		mark();
	}
	@Test
	public void unmarkAnyFile() throws Exception
	{
		enterDest("评论");
		unmark();
	}
	/*
	@Test
	public void markAnyFolder() throws Exception
	{
		enterDest("根目录");
		mark();
	}
	@Test
	public void unmarkAnyFolder() throws Exception
	{
		enterDest("根目录");
		unmark();
	}*/
  public void mark() throws Exception {
	// enterDest("评论");
	 int i=0;
	 while(isMark(i))
	 {	i++;
	 	if(i>6)
	 	{	System.out.println("找不到没有收藏的文件，请检查！");
	 		break;}
	 }
	 manageFile(i,"收藏");
	 assertTrue(isMark(i),"没有收藏成功！"); 
	 WebElement e1 = driver.findElementByAndroidUIAutomator("new UiSelector().description(\"file\").instance("+i+")");
	 String fileName= e1.getText();
	clickName("常用");
	clickName("收藏");
	assertTrue(exist(fileName),"收藏列表不存在！");  
  }
  

  public void unmark() throws Exception
  {	 //  enterDest("评论");
		 int i=0;
		 while(!isMark(i))
		 {	i++;
		 	if(i>6)
		 	{	System.out.println("找不到已收藏的文件，请检查！");
		 		break;}
		 }
		 WebElement e1 = driver.findElementByAndroidUIAutomator("new UiSelector().description(\"file\").instance("+i+")");
		 String fileName= e1.getText();
		 System.out.println("文件名"+fileName);
		 manageFile(i,"取消收藏");
		 assertTrue(!isMark(i),"没有取消收藏！"); 	 
		clickName("常用");
		clickName("收藏");
		Thread.sleep(3000);
		assertTrue(!isMark(fileName),"收藏列表存在！");  
  }
  public boolean isMark(int rank)
  {
	    try
		  {
			 WebElement e1= driver.findElementByAndroidUIAutomator("new UiSelector().description(\"file\").instance("+rank+")").findElement(By.id("com.eisoo.anyshare:id/iv_file_collect"));
			
			 return true;
		  }
		  catch(Exception e)
		  {
			  return false;
		  }  	 		
	  }
  //如果收藏文件夹可以用此方法
  public boolean isMark(int rank, String type)
  {
	  if(type=="文件")
	  {
		  try
		  {
			 WebElement e1= driver.findElementByAndroidUIAutomator("new UiSelector().description(\"file\").instance("+rank+")").findElement(By.id("com.eisoo.anyshare:id/iv_file_collect"));
			
			 return true;
		  }
		  catch(Exception e)
		  {
			  return false;
		  }  
	  }
	  else if(type=="文件夹")
	  {
		  try
		  {
			  folder(rank).findElement(By.id("com.eisoo.anyshare:id/iv_file_collect")); 
			  return true;
		  }
		  catch(Exception e)
		  {
			  return false;
		  }
	
	  }
	  else 
		  System.out.println("类型错误!");
		  return false;
	
	  
	   
  }
  public boolean isMark(String fileName)
  {
	  try
	  {
		  WebElement e1=driver.findElementByName(fileName).findElement(By.id("com.eisoo.anyshare:id/iv_file_collect"));  
		  return true;
	  }
	  catch(Exception e)
	  {
		  System.out.println("此文件在收藏列表中已不存在");
		  return false;
	  }
  }
  
  public WebElement file(int rank)
  {
	  return driver.findElementByAndroidUIAutomator("new UiSelector().description(\"file\").instance("+rank+")");
  }
  
  public WebElement folder(int rank)
  {
	  return driver.findElementByAndroidUIAutomator("new UiSelector().description(\"folder\").instance("+rank+")");
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
  public void afterClass() {
	  driver.quit();
  }
  
  

}
