package com.eisoo.anysharetest;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UploadTest extends Functions {
	

	  @BeforeClass
	  public void beforeClass() throws Exception {
		//  System.out.println("BeforeClass");
		  try
		  { init();}
		  catch(Exception e)
		  { 
			//  e.printStackTrace();
			  System.out.println("init 失败");
			  driver.quit();		  
			  init();
		  }
		  assertTrue(loginTest("116.236.224.243","zfy","123123"),"登录失败！");
		
	  }

	  @AfterClass
	  public void afterClass() {
		//  System.out.println("AfterClass");
		  driver.quit();
	  }
	  
	  @BeforeMethod
	  public void beforeMethod() throws Exception {
		  startAS();
	  }

	  @AfterMethod
	  public void afterMethod() throws Exception {
		  killAS();
		 // Thread.sleep(2000);
	  }

	  @BeforeTest
	  public void beforeTest() throws Exception {
		  System.out.println("BeforeTest");
		
	  }

	  @AfterTest
	  public void afterTest() {
	  }
	//public boolean upload(int num ,int waitwifi,String type)
	  @Test
	  public void uploadVideo() throws Exception
	  {   System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  enterDest("上传");
	  	  assertTrue(upload(1,1,"上传视频"),"上传失败") ;  
	  	  clearUploadList();
	  }
	  @Test 
	  public void uploadAudio() throws Exception
	  {   System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  enterDest("上传");
	  	  assertTrue(upload(1,1,"上传音频"),"上传失败");
	  	  clearUploadList();  
	  }
	  @Test 
	  public void uploadFile() throws Exception
	  {   System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  enterDest("上传");
	  	  assertTrue(upload(1,1,"上传文件"),"上传失败");
	  	  clearUploadList();  
	  }

	  	  
	  @Test 
	  public void uploadTakePhoto() throws Exception
	  {   System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  enterDest("上传");
	  	  assertTrue(upload(1,1,"拍摄上传"),"上传失败");
	  	  clearUploadList();  
	  }
	    
	  	  
	  @Test 
	  public void uploadPics() throws Exception
	  {   System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  enterDest("上传");
	  	  assertTrue(upload(1,1,"上传照片"),"上传失败");
	  	  clearUploadList();  
	  }

	  @Test //2017-12-15
	  public void uploadWaitWifi() throws Exception
	  {   System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  startAS();
	  	  driver.pressKeyCode(3);
	  	  switchWifi(0);
	  	  enterDest("上传");
	  	  assertTrue(upload(1,1,"上传视频"),"上传失败");
	  	  assertTrue(exist("等待Wi-Fi..."),"不存在等待Wi-Fi标记");
	  	  clearUploadList(); 
	  	  switchWifi(1);
	  }

	  @Test //2017-12-17
	  public void uploadNoWifi() throws Exception
	  {   System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  switchWifi(0);
	  	  enterDest("上传");
	  	  assertTrue(upload(1,0,"上传视频"),"上传失败");
	  	  assertTrue(!exist("等待Wi-Fi..."),"存在等待Wi-Fi标记");
	  	  clearUploadList(); 
	  	  switchWifi(1);
	  }

	  @Test
	  //多选只针对视频／音频／文件有效
	  public void uploadMulVideo() throws Exception
	  {   System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  enterDest("上传");
	  	  assertTrue(upload(6,1,"上传视频"),"上传失败") ;  
	  	  clearUploadList();
	  }

	  @Test
	  public void uploadAllVideo() throws Exception
	  {   System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  enterDest("上传");
	  	  assertTrue(upload(10,1,"上传视频"),"上传失败") ;  
	  	  clearUploadList();
	  }

	  @Test
	  public void uploadPauseVideo() throws Exception
	  {   System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  enterDest("上传");
	  	  assertTrue(upload(10,1,"上传视频"),"上传失败") ;  
	  	  assertTrue(pause("上传",0),"未暂停");
	  	  clearUploadList();
	  }
	  @Test
	  public void uploadContinueVideo() throws Exception
	  {   System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  enterDest("上传");
	  	  assertTrue(upload(10,1,"上传视频"),"上传失败") ; 
	  	  assertTrue(!pause("上传",1),"未暂停");
	  	  clearUploadList();
	  }
  	  
	  @Test
	  public void uploadNoPermission() throws Exception
	  {   	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
	  		enterNoPermission("");
	  		assertTrue(!upload(10,1,"上传视频"),"上传失败") ;  
	  	//  clearUploadList();
	  }
	  
	  @Test
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
	  


}

