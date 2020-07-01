package com.eisoo.anysharetest;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


//主要实现重命名，删除，搜索

public class FilelistTest extends Functions {


	  @BeforeClass
	  public void beforeClass() throws Exception {
		  System.out.println("BeforeClass"+this.getClass().getName());
		  try
		  { init();}
		  catch(Exception e)
		  { 
			 // e.printStackTrace();
			  System.out.println("init 失败");
		//	  driver.quit();
			  init();
		  } 
		  assertTrue(loginTest("116.236.224.243","zfy","123123"),"登录失败！");
	  }

	  @AfterClass
	  public void afterClass() {
		  driver.quit();
//		  System.out.println("filelist AfterClass");
		 
	  }
		
	  @AfterMethod
		  public void afterMethod() throws Exception {
			  killAS();
			//  killAS();   
		  }

	   @Test
	  public void delFile() throws Exception{
			  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			  enterDest("上传");
			  String fileName=manageFile(1,"删除");
			  clickName("确定");
			  assertTrue(!exist(fileName),"已删除!");  
	   }
		@Test
	 	public void delFiles() throws Exception
	 	{System.out.println("*************"+Thread.currentThread().getStackTrace()[1].getMethodName());
	 	  enterDest("上传");
		  manageFiles(2,"删除");
		  clickName("确定");
	 	}
		@Test
		public void renameFile() throws Exception
		{	System.out.println("*************"+Thread.currentThread().getStackTrace()[1].getMethodName());
			enterDest("文件列表");
		 	manageFile(2,"重命名");
		 	WebElement e1= driver.findElementByClassName("android.widget.EditText");
		 	String fname1=e1.getText();
		 	e1.sendKeys(fname1+"1");
		 	clickName("确定"); 
		  }


	/*	public void openFile() throws Exception
		{
			enterDest("文件类型");
			clickFile("excel");		
		}*/
		@Test
	   public void searchKws() throws Exception
	   {System.out.println("*************"+Thread.currentThread().getStackTrace()[1].getMethodName());
		  search("04","当前目录");
	   }
		@Test
	   public void searchAlldir() throws Exception
	   {	System.out.println("*************"+Thread.currentThread().getStackTrace()[1].getMethodName());
		  search("04","所有目录");
	   }
	   @Test
	   public void asearchInlink() throws Exception
	   {	System.out.println("*************"+Thread.currentThread().getStackTrace()[1].getMethodName());
		   search("AnyShare://vivi/.自动化目录/文件类型/word/Android自动化测试.doc","内链路径");
		   switchInput(1);
		   startAS();
		   killAS();  
	   }
	   
	   @Test
	   public void searchExlink() throws Exception
	   {	
		   System.out.println("*************"+Thread.currentThread().getStackTrace()[1].getMethodName());
		  search("http://116.236.224.243:80/link/7F353C66D9131673ED85316ADA566591 有效期限：2018-02-10 ","外链地址");
		  switchInput(1);
		  startAS();
		  killAS();
	   }
	 /*  public void search(String keyWs,String type) throws Exception 
	   {
		enterDest("文件类型");
		WebElement searchText1=driver.findElementById("com.eisoo.anyshare:id/ll_searchView");
		searchText1.click();
		Thread.sleep(2000);
		WebElement searchText2=driver.findElementById("com.eisoo.anyshare:id/et_search_content");
		if(type!="当前目录")
		{
			clickName(type);		
		}
		searchText2.sendKeys(keyWs);//所有目录都需要切换输入法
		if(type=="内链路径"||type=="外链地址")
		{
			switchInput(0);
			startAS();		
			driver.swipe(990, 1740, 990, 1740, 200);
		}
		Thread.sleep(3000);
		*//****************以下是对结果的判断***********//*
		if(type=="外链地址")
		{
			assertTrue(exist(driver.findElementByClassName("android.webkit.WebView")));
		}
		else
		{
			assertTrue(fileContain(keyWs.substring(keyWs.lastIndexOf("/")+1,keyWs.length())),"没有这个文件");
	   }
	   }*/
		 
	}
