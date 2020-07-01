package com.eisoo.anysharetest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class openFileTest extends Functions{
  @Test
  public void openVideo() throws Exception {
	enterDest("文件类型");
	clickFile("视频");
	clickFile(1);
	Thread.sleep(15000);
	String progress=driver.findElementById("com.eisoo.anyshare:id/video_time_current").getText();
	System.out.println("当前进度为："+progress);
	assertTrue(!progress.equals("00:00"),"进度为0");  
  }
  
 @Test
  public void openAudio() throws Exception {
	enterDest("文件类型");
	clickFile("音频");
	clickFile(1);
	Thread.sleep(15000);
	String progress=driver.findElementById("com.eisoo.anyshare:id/tv_currentPosition").getText();
	System.out.println("当前进度为："+progress);
	assertTrue(!progress.equals("00:00"),"进度为0");  
  }
  
  @Test
  public void openFile() throws Exception
  {
	  enterDest("文件类型");
	  clickFile("word");
	  clickFile(1);
	  Thread.sleep(10000);
	  clickName("用第三方应用打开");
	  clickName("WPS Office");
	  Thread.sleep(6000);
	  assertTrue(exist("编辑"),"文件未打开!"); 
	  killAS();
  }
  
  @Test
  public void openPic() throws Exception
  {
	  enterDest("文件类型");
	  clickFile("图片");
	  clickFile(1);
	  Thread.sleep(6000);
	  WebElement e1=driver.findElementById("com.eisoo.anyshare:id/iv_photo");
	  assertTrue(exist(e1),"文件未打开!");  
  }
  
  @Test//按钮打开
  public void d_openExcel() throws Exception {
	  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
	  enterDest("文件类型");
	  clickFile("excel");
	  manageFile(0,"打开");
	  Thread.sleep(10000);
	  clickName("WPS Office");	
	  Thread.sleep(12000);
	  try{
		  driver.findElementByName("编辑");
	  }
	  catch (Exception e)
	  {
		  Thread.sleep(5000);  
	  }
	  if(exist("不保存"))  //new
	  { clickName("不保存");}
	  Thread.sleep(1000);
	  assertTrue(exist("编辑"),"文件未打开!");  
	  killAS();
	  Thread.sleep(1000);
	  if(exist("不保存"))  //new
	  { clickName("不保存");}
	  killAS();
  }

/*//只能1到8
  public String clickFile(int rank)
  {
	
	  WebElement e2=driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.eisoo.anyshare:id/tv_file_name\").instance("+rank+")");
	  String fileName=e2.getText();
	  System.out.println("准备操作文件:"+e2.getText());
	  e2.click();
	  return fileName;
	  
  }*/
  @BeforeClass
  public void beforeClass() throws Exception {
	  try
	  { init();}
	  catch(Exception e)
	  { 
		//  e.printStackTrace();
		  System.out.println("init 失败");
	//	  driver.quit();		  
		  init();
	  }
	  assertTrue(loginTest("116.236.224.243","zfy","123123"),"登录失败！");
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  @AfterMethod
  public void afterMethod() throws Exception{
	  killAS();
	  killAS();
  }

}
