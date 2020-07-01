package com.eisoo.anysharetest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class InlinkTest extends Functions {
	
	  @Test
	  public void n1_addOne() throws Exception{
		  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		  enterDest("共享");  
		  manageFile(2,"内链共享");
		  WebElement e1= driver.findElementByName("保存");
		  assertTrue(!e1.isEnabled(),"当前保存按钮为可用状态，未编辑应该不可用！");
		  if(!nameExist("王十一"))
		  { addVisitor();
		  	Thread.sleep(2000);
			manageFile(2,"内链共享");
			assertTrue(nameExist("王十一"),"不存在此用户名"); 
			}
		  killAS();
	  }
	
	  @Test
	  public void n1_checkAdd() throws Exception
	  {	  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		  startAS();
		  switchAccount("王十一","123123");
		  clickName("共享文档");
		  assertTrue(exist("vivi"),"不存在被分享的文件");
		  clickName("我的");
		  clickName("我的消息");
		  startAS();
		  WebElement e1= (WebElement) driver.findElementsById("com.eisoo.anyshare:id/tv_file_send").get(0);
		  assertTrue(e1.getText().contains("vivi给您共享了"),"第一条记录不是共享");   
	  }
	  
	  @Test
	  public void n2_delOne() throws Exception{
		  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		  Thread.sleep(2000);
		  startAS(); 
		  switchAccount("zfy","123123");
		  enterDest("共享");  
		  manageFile(2,"内链共享");
		  WebElement e1= driver.findElementByName("保存");
		  assertTrue(!e1.isEnabled(),"当前保存按钮为可用状态，未编辑应该不可用！");
		  if(nameExist("王十一"))
		  { delVisitor();
		     Thread.sleep(2000);
		     manageFile(2,"内链共享");
			 assertTrue(!nameExist("王十一"),"不存在此用户名");
		  }
		  killAS();
	  }
	
	  @Test
	  public void n3_checkdel() throws Exception
	  {	  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		  startAS();
		  switchAccount("王十一","123123");
		  startAS();
		  clickName("我的");
		  clickName("我的消息");
		  startAS();
		  WebElement e1= (WebElement) driver.findElementsById("com.eisoo.anyshare:id/tv_file_send").get(0);
		  assertTrue(e1.getText().contains("vivi给您取消共享"),"第一条记录不是共享");   
	  }
	  
	  // 目的是为了将账号切回来继续测试，检查保存按钮默认状态
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
	  /*
	  @Test
	  public void Add() throws Exception
	  {
		  switchAccount("王十一","123123");
		  clickName("共享文档");
		  assertTrue(exist("vivi"),"不存在被分享的文件");
		  clickName("我的");
		  clickName("我的消息");
		  startAS();
		  WebElement e1= (WebElement) driver.findElementsById("com.eisoo.anyshare:id/tv_file_send").get(0);
		  assertTrue(e1.getText().contains("vivi给您共享了"),"第一条记录不是共享");   
	  }*/
	    

  
	
	

  //20180522
  public void  switchAccount(String name,String psd) throws Exception
  {
	  logout();
	  Thread.sleep(3000);
	  driver.findElementById("com.eisoo.anyshare:id/et_account").sendKeys(name);
	  driver.findElementById("com.eisoo.anyshare:id/et_password").sendKeys(psd);
	  driver.pressKeyCode(4);
	  clickName("登录");
	  Thread.sleep(3000);
	  startAS();   
  }
  
 
  
  public boolean nameExist(String name) throws Exception
	{
	    Thread.sleep(2000);
			if(exist(name))
				return true;
		else
		{
				for(int i=0;i<5;i++)
		  		{  System.out.println("滑动"+i+"次");
		  			swipe("down");
		  			if(exist(name))
		  				return true;
		  		}
				return false;
		}
	}
  
  
  public void delVisitor() throws Exception
  {
	  clickFile("删除") ;
	  clickName("保存"); 
  }
  
  //通用的方式是找到父节点，在找子节点
  public void addVisitor() throws Exception
  {
	  clickName("添加访问者");
	  clickFile("ZZ");
	  clickId("com.eisoo.anyshare:id/rl_checkbox");

	  clickId("com.eisoo.anyshare:id/tv_ok");
	  clickName("保存");
	  Thread.sleep(1000);
  }
	// WebElement e1= driver.findElementByXPath("//com.eisoo.anyshare:id/tv_name[@text=name]");new Uiselector().
	//  WebElement e1=driver.findElementByAndroidUIAutomator("new UiSelector().text("+name+").fromParet(new Uiselector().className(\"android.widget.RelativeLayout\").getchild(new Uiselector().resourceId(\"com.eisoo.anyshare:id/rl_checkbox\")");
	
	//  System.out.println(e1.getText());
/* // @Test
  public void tests1() throws Exception
  {
	//  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  enterDest("共享");  
	  manageFile(1,"内链共享");
	  addVisitor();
	  Thread.sleep(3000);
  }
  */
  
  @BeforeClass
  public void beforeClass() throws Exception {
	  init();
	  loginTest("116.236.224.243","zfy","123123");
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  @BeforeMethod
  public void beforeMethod() throws Exception {
	  startAS();
  }

  @AfterMethod
  public void afterMethod() throws Exception {
	  killAS(); }

}
