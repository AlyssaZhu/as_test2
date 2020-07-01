package com.eisoo.anysharetest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import java.util.Collection;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;

public class UITest extends Functions {
  @Test
  public void checkUI() throws Exception {
	  startAS();
	  assertTrue(exist("文档"),  "no 文档!");
	  assertTrue(exist("常用"),  "no 常用!");
	  assertTrue(exist("传输"),  "no 传输!");
	  clickName("我的");
	  clickId("com.eisoo.anyshare:id/ll_personal_userinfo");
	  checkInfo();
	  driver.pressKeyCode(4);
	  Thread.sleep(2000);
	  checkSets();  
  }
  
  //可继续扩展
 public void checkInfo()
  {
	  Collection coll=driver.findElementsByClassName("android.widget.TextView");
/*	  int num=driver.findElementsByClassName("android.widget.TextView").size();
	  System.out.println(num);
	  WebElement e1=(WebElement) driver.findElementsByClassName("android.widget.TextView").get(4);
	  System.out.println(e1.getText());
	  WebElement e2=(WebElement) driver.findElementsByClassName("android.widget.TextView").iterator().next();
	  System.out.println(e2.getText());*/
	  assertTrue((getE(4).getText().equals("zfy")),"Error:用户名不正确");
	  assertTrue((getE(6).getText().equals("vivi")),"Error:显示名不正确");
	  
  }                                                                     
 public void checkSets() throws Exception
 {
	 assertTrue((exist("我的消息")&&exist("设置")&&exist("版本信息")),"设置项缺失");
	 clickName("设置");
	 assertTrue(exist("清除缓存"),"error");
	 assertTrue(exist("默认下载位置"),"error");
	 
 }
 
 public WebElement getE(int i)
 {
	 WebElement e1=(WebElement) driver.findElementsByClassName("android.widget.TextView").get(i-1);
	System.out.println(e1.getText());
	 return e1;
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
