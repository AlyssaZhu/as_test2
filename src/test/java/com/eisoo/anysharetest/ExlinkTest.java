package com.eisoo.anysharetest;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;

public class ExlinkTest extends Functions {
 
	public boolean  setExlink(int type) throws Exception
	 {
		  WebElement status=driver.findElementById("com.eisoo.anyshare:id/tv_share_state");
		  System.out.println(status.getText());
		  if((status.getText().equals("未开启")&&type==1)||(status.getText().equals("已开启")&&type==0))
		  {
			  status.click();
			  clickId("com.eisoo.anyshare:id/cb_share_open_link");
			  Thread.sleep(1000);
			  clickName("保存");
			  Thread.sleep(3000);
		  }
		  return exist("已开启");
	  }

	public void exlinkPaste() throws Exception
	 {		
		  Thread.sleep(2000);
		  clickId("com.eisoo.anyshare:id/ll_searchView");
		  clickName("外链地址");
		  WebElement e1= driver.findElementById("com.eisoo.anyshare:id/et_search_content");
		  TouchAction ta = new TouchAction(driver);
	      ta.longPress(e1).release().perform(); // 长按
	      Thread.sleep(2000);
	      driver.swipe(170, 330, 170, 330, 100);
	      System.out.println("2");
	      Thread.sleep(3000);
	 }
	  
	 
  public boolean manExlink(String type) throws Exception {
	  setExlink(1);
	  clickName(type);
	  switch(type)
	  {
	  case"复制外链":
		  clickName("复制外链");
		  driver.pressKeyCode(4);
		  Thread.sleep(2000);
		  exlinkPaste();
          return !exist("搜索外链地址");
		  //搜索外链进行验证;
	  case"短信":
		 driver.findElementById("com.android.mms:id/recipients_editor").sendKeys("17301609232"); ;
		 driver.findElementByAccessibilityId("发送").click();
		 int count=driver.findElementByClassName("android.widget.EditText").getText().length();
		 System.out.println(count);
		 return(count<10);
	  case"微信好友":
		  clickName("Vivi");
		  clickName("分享");
		  clickName("返回爱数AnyShare");
		  return true;
	  case"微信朋友圈":
		  clickName("微信朋友圈");
		  clickName("发表");
		  return true;
	  case"QQ":
		  Thread.sleep(1000);
		  startAS();
		  clickName("move");
		  clickName("发送");
		  clickName("返回爱数 AnyShare");
		  return true;
	  case"邮件":
		  driver.findElementById("com.android.email:id/chips_edit").sendKeys("125426647@qq.com");
		  clickId("android:id/icon2");
		  startAS();
			return exist("新浪微博");
		  
	  case"新浪微博":
   	    Thread.sleep(4000);
		clickName("发送");
		startAS();
		return exist("新浪微博");
	  default: 
			 return false;
	  } 
  }
  

  @Test
  public void m_shareMMS() throws Exception
  {		
	  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
	  enterDest("共享");
	  manageFile(0,"外链分享");
	  assertTrue(manExlink("短信"),"未完成短信分享");  
	  killAS();
  }
  
  @Test
  public void m_shareWechat() throws Exception
  {
	  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
	  enterDest("共享");
	  manageFile(0,"外链分享");
	  assertTrue(manExlink("微信好友"),"未完成微信分享");  
  }
  
  @Test
  public void m_shareQQ() throws Exception
  {
	  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
	  enterDest("共享");
	  manageFile(0,"外链分享");
	  assertTrue(manExlink("QQ"),"未完成QQ分享");  
  }
  
  @Test
  public void m_shareWeibo() throws Exception
  {
	  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
	  enterDest("共享");
	  manageFile(0,"外链分享");
	  assertTrue(manExlink("新浪微博"),"未完成微博分享");  
  }
  
  @Test
  public void m_shareEmail() throws Exception
  {
	  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
	  enterDest("共享");
	  manageFile(0,"外链分享");
	  assertTrue(manExlink("邮件"),"未完成邮件分享");  
  }
  
  @Test
  public void m_copyExlink() throws Exception
  {
	  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
	  enterDest("共享");
	  manageFile(0,"外链分享");
	  assertTrue(manExlink("复制外链"),"未完成复制外链");  
  }
 
  @Test
  public void m_closeExlink() throws Exception
  {
	  enterDest("共享");
	  manageFile(1,"外链分享");
	  assertTrue(setExlink(1),"未开启");
	  driver.pressKeyCode(4);
	  manageFile(1,"外链分享");
	  assertTrue(!setExlink(0),"未关闭");  
  }
  

  @BeforeMethod
  public void beforeMethod() throws Exception {
	  startAS();
  }

  @AfterMethod
  public void afterMethod() throws Exception {
	  killAS();
  }
  @BeforeClass
  public void beforeClass() throws Exception {
	  init();
	  loginTest("116.236.224.243","zfy","123123");
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  
}
