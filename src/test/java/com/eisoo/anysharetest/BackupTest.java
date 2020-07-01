package com.eisoo.anysharetest;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BackupTest extends Functions {
	

/*	检查点设置:
	开关有无开启
	传输列表是否有备份任务
	备份进度是否更新*/

	
	//backup
	@Test
	public void j1_checkdefaultUI() throws Exception
	{
		 System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		 startAS();
		 clickName("传输");
		 clickName("上传列表");
		 assertTrue(!exist("自动备份任务"),"error1：默认是开启状态!");
		/* clickName("我的");
		 clickName("备份设置");	*/ 
		 enterSet("备份设置");
		 assertTrue(!checkBackupBt(),"error2:默认是开启状态！"); 
	}
	
	@Test
	public void j2_startBackup() throws Exception
	{
		System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		startAS();
		enterSet("备份设置");
		assertTrue(setBackup(1),"error1：按钮未开启");
		driver.pressKeyCode(4);
		Thread.sleep(2000);
		driver.pressKeyCode(4);
		clickName("传输");
		clickName("上传列表");
		assertTrue(exist("自动备份任务"),"error2：开启后上传列表没有备份任务!");
		WebElement e=driver.findElementById("com.eisoo.anyshare:id/tv_backup_title");
		int num1=num(e.getText());
		System.out.println("剩余备份数"+num1);	
		Thread.sleep(15000);
		int num2=num(e.getText());
		System.out.println("剩余备份数"+num2);	
		assertTrue((num1>num2||num1==0),"error3:没有进行备份！");
	}
	
	/*测试暂停备份，继续备份*/
	@Test
	public void j3_pauseBackup() throws Exception
	{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		startAS();
		clickName("传输");
		clickName("上传列表");
		if(!exist("自动备份任务"))
		{
			enterSet("备份设置");
			setBackup(1);
			driver.pressKeyCode(4);
			Thread.sleep(2000);
			driver.pressKeyCode(4);
			clickName("传输");
			clickName("上传列表");
		}
		//新增判断，备份完成时，切换相册继续备份
		if(exist("照片自动备份完成"))
		{ enterSet("备份设置");
		  choosePics("max");
		  Thread.sleep(2000);
			driver.pressKeyCode(4);
			clickName("传输");
			clickName("上传列表");
			}
		assertTrue(pauseBackup(1),"error1:没有暂停");
		Thread.sleep(2000);
		assertTrue(!pauseBackup(0),"error2:暂停了");
	}
	
	@Test
	public void j4_wifiBackup() throws Exception
	{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		startAS();
		clickName("传输");
		clickName("上传列表");
		if(!exist("自动备份任务"))
		{
			enterSet("备份设置");
			setBackup(1);
			driver.pressKeyCode(4);
			Thread.sleep(2000);
			driver.pressKeyCode(4);
			clickName("传输");
			clickName("上传列表");
		}
		//新增判断，备份完成时，切换相册继续备份
		if(exist("照片自动备份完成"))
		{ enterSet("备份设置");
		  choosePics("max");
		  Thread.sleep(2000);
			driver.pressKeyCode(4);
			clickName("传输");
			clickName("上传列表");
			}
		switchWifi(0);
		Thread.sleep(1000);
		startAS();	
		assertTrue(exist("等待Wi-Fi..."),"error:非wifi 下没有等待");
		switchWifi(1);
		Thread.sleep(1000);
		startAS();	
		assertTrue(exist("正在上传...")||exist("照片自动备份完成"),"error:wifi 下没有继续");
	}
	
	/*最好选择数目比较小的相册进行备份已方便快速备份完成
	取消原来的相册，选择数目较少的相册*/
	@Test
	public void j5_chooseDest() throws Exception
	{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		startAS();
		enterSet("备份设置");
		assertTrue(setBackup(1),"error1：按钮未开启");
		choosePics("mini");
		Thread.sleep(2000);
		driver.pressKeyCode(4);
		clickName("传输");
		clickName("上传列表");
		Thread.sleep(30000);	
		assertTrue(exist("照片自动备份完成"),"30s内备份不到9张照片！");
	}
	@Test
	public void j6_stopBackup() throws Exception
	{	
		 System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		startAS();
		enterSet("备份设置");
		assertTrue(!setBackup(0),"error1：按钮仍然开启");
	}
	
/*  public void start() throws Exception {
	//这句话在这不适用  driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	 
	  status.click();
	  clickName("确定");
	  driver.pressKeyCode(4);
	  clickName("传输");
	 // startAS();
	  assertTrue(exist("自动备份任务"),"error3：开启后无备份任务!");
	  System.out.println(driver.findElementById("com.eisoo.anyshare:id/tv_backup_title").getText());
	  //暂停，观察暂停时状态按钮没有变化属性；
	 
	  String backupstatus=e2.getAttribute("clickable");
	  boolean pause=Boolean.parseBoolean(backupstatus);
	  assertTrue(!pause,"error:当前是暂停状态");
	  assertTrue(exist("正在上传..."),"error：状态标签不正确");
	  WebElement e2=driver.findElementById("com.eisoo.anyshare:id/rl_backup_progress");
	  e2.click();
	 // assertTrue(pause,"error:没有暂停状态");
	  assertTrue(exist("已暂停"),"error：状态标签不正确");
	  e2.click();
	//  assertTrue(!pause,"error:没有暂继续备份");
	  assertTrue(exist("正在上传..."),"error：状态标签不正确");
	  switchWifi(0);
	 startAS();
	 assertTrue(exist("等待Wi-Fi..."),"不存在等待Wi-Fi标记");
	 switchWifi(1);
	 startAS();
	 clickId("com.eisoo.anyshare:id/tv_backup_title");
	 assertTrue(exist("我的相册"),"未能进入我的相册");
  }
	public void renameFile() throws Exception
	{	System.out.println("*************"+Thread.currentThread().getStackTrace()[1].getMethodName());
		enterDest("文件列表");
	 	manageFile(2,"重命名");
	 	WebElement e1= driver.findElementByClassName("android.widget.EditText");
	 	String fname1=e1.getText();
	 	e1.sendKeys(fname1+"1");
	 	clickName("确定"); 
	  }*/

	//@Test
  public void renamePics() throws Exception
  {
	  startAS();
	  clickFile("vivi");
	 /*  manageFile(0,"重命名");
	  WebElement e1= driver.findElementByClassName("android.widget.EditText");
	 	String fname1=e1.getText();
	 	e1.sendKeys(fname1+"1");
	 	clickName("确定"); 
	  */
	  rename(0);
	//  startAS();
	  clickFile("我的相册1");//比较数目是否增加
	  clickId("com.eisoo.anyshare:id/rl_multiple_select");
	  clickName("全选");
	  WebElement e=driver.findElementById("com.eisoo.anyshare:id/tv_title");
	 int num1=num(e.getText());
	 Thread.sleep(10000);
	 clickName("全选");
	 int num2=num(e.getText());
	 System.out.println("照片数目由"+num1+"增加至"+ num2);
	 assertTrue((num2>num1),"num2 不大于num1");  
  }
  
  //从标题中筛选数字
/*  public int num(String name)
  {
		 String str1="";
		 int num1=0;
		 int len1=name.length();
		 if(len1>2)
		 {	 for(int i=0;i<len1;i++)
			 {
				 if(name.charAt(i)>=48 && name.charAt(i)<=57)
				 {
					 str1+=name.charAt(i);
				 }
			 }
		  num1=Integer.parseInt(str1); }
		 return num1; 
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
