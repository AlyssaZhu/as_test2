package com.eisoo.anysharetest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class CommtTest extends Functions
{
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
		  killAS();
	  }
	  
	  @AfterClass
	  public void afterClass() {
		//  System.out.println("AfterClass");
		  driver.quit();
	  }
	  // 先统计评论数
	  @Test
	  public void addcomm_diffChar() throws Exception
	  {
		  String content="123456788ABCabc 评论！"  ;
		  assertTrue(addComment(content),"没有添加成功");
		  assertTrue(exist(content),"没有找到评论内容");
		  
	  }
	  @Test
	  public void addcomm_overlimit() throws Exception
	  {
		 
		  System.out.println("Case:"+Thread.currentThread().getStackTrace()[1].getMethodName());
		  String content="hghhjkjkjkkkjjjjhxjjjjhghggfgffffffffdffff"
			  		+ "gggggghghhhhjjjkjgsggssghhjksjsjlskkskskssjksjhshjsjhkhgjg"
			  		+ "fgfhfffghgfhghgjhjjhkjjhjhhjhggfffffff1234ass我来了就看看hghhj"
			  		+ "kjkjkkkjjjjhxjjjjhghggfgffffffffdffffgggggghghhhhjjjkjgsggss"
			  		+ "gfgffffffffdffffgggggghghhhhjjjkjgsggssghhjksjsjlskk"
			  		+ "skskssjksjhshjsjhghhjkjkjkkkjjjjhxjjjjhghggfgffffffffdffffggggg"
			  		+ "ghghhhhjjjkjgsggssghhjksjsjlskkskskssjksjhshjsjhghhjkjkjkkkjjjj";
		  assertTrue(!addComment(content),"没有添加成功");
	  }
	  @Test
	  public void addcomm_inlimit() throws Exception
	  {
		  System.out.println("Case:"+Thread.currentThread().getStackTrace()[1].getMethodName());
		  assertTrue(addComment("1234ass我来了就看看还好手机健康快乐健康 回家看见慷慨解囊阿哈哈哈哈哈是谁 收拾收拾收拾收拾事实上收拾收拾家就看见空间看看"),"没有添加成功");
	  }
	  
	  @Test
	  public void delcommt() throws Exception
	  {
		  assertTrue(delComment(),"没有删除成功");
	  }
	  @Test
	  public void replycommt() throws Exception
	  {
		  assertTrue(replyComment("回复评论ZZ！"),"");
	  }
	  public boolean addComment(String content ) throws Exception
	  {
		  System.out.println("*****---------"+Thread.currentThread().getStackTrace()[1].getMethodName());
		 enterDest("评论");
		 manageFile(0,"评论");
		 startAS();// 不重新进入 ，检测不到。
		 WebElement title=driver.findElementById("com.eisoo.anyshare:id/tv_comment_title_top");
		 String title1=title.getText();
		 String str1="";
		 int len1=title1.length();
		 if(len1>2)
			 for(int i=0;i<len1;i++)
			 {
				 if(title1.charAt(i)>=48 && title1.charAt(i)<=57)
				 {
					 str1+=title1.charAt(i);
				 }
			 }
		 int num1=Integer.parseInt(str1);
		 System.out.println(num1);
		 WebElement e1=driver.findElementById("com.eisoo.anyshare:id/et_commment");
		 e1.sendKeys(content);
		 clickName("发送");
		 startAS();
		 String title2 = title.getText();
		 int len2 = title2.length();
		 String str2="";
		 if(len2>2)
			 for(int i=0;i<len1;i++)
			 {
				 if(title2.charAt(i)>=48 && title2.charAt(i)<=57)
				 {
					 str2+=title2.charAt(i);
				 }
			 }
		 int num2=Integer.parseInt(str2);
		 
		 System.out.println(num2);

		if(num2==(num1+1))
		{
			return true;
		}
		else
		{
			return false;
		}	 
	  }
	  
	 // @Test
	  public boolean delComment() throws Exception
	  {
		     System.out.println("*****-------------"+Thread.currentThread().getStackTrace()[1].getMethodName());
			 enterDest("评论");
			 manageFile(0,"评论");
			 startAS();
			 WebElement title=driver.findElementById("com.eisoo.anyshare:id/tv_comment_title_top");
			 int num1=num(title.getText());
			 System.out.println(num1);
			 if(num1==0)
			 { System.out.println("当前没有评论，请检查操作条件");
				 return false;}
			 WebElement comm = driver.findElementByAndroidUIAutomator("new UiSelector().description(\"comment\").instance(0)");
			 longPress(comm);
			 try
			 {
				 clickName("删除");
			 }
			 catch (Exception e)
			 {
				 System.out.println("没有删除项");
			 }
			 startAS(); 
			 int num2=num(title.getText());
			 System.out.println(num2);
			 if(num1==num2+1)
				 return true;
			 else 
				 return false;
			 
		//	 WebElement e1=driver.findElementById("com.eisoo.anyshare:id/et_commment");		 
	  }
	  
	  public boolean replyComment(String content) throws Exception
	  {
		    System.out.println("*****------------"+Thread.currentThread().getStackTrace()[1].getMethodName());
			 enterDest("评论");
			 manageFile(0,"评论");
			 startAS();
			 WebElement title=driver.findElementById("com.eisoo.anyshare:id/tv_comment_title_top");
			 int num1=num(title.getText());
			 System.out.println(num1);
			 if(num1==0)
			 { System.out.println("当前没有评论，请检查操作条件");
				 return false;}
			 WebElement comm = driver.findElementByAndroidUIAutomator("new UiSelector().description(\"comment\").instance(0)");
			 longPress(comm);
			 try
			 {
				 clickName("回复");
				 WebElement e1=driver.findElementById("com.eisoo.anyshare:id/et_commment");
				 e1.sendKeys(content);
				 clickName("发送");
			 }
			 catch (Exception e)
			 {
				 System.out.println("没有评论供删除/回复/拷贝");
			 }
			 startAS(); 
			 int num2=num(title.getText());
			 System.out.println(num2);
			 if(num1==num2-1)
				 return true;
			 else 
				 return false;
	  }
	  
	  public boolean copyComment() throws Exception
	  {
		    enterDest("评论");
			 manageFile(0,"评论");
			 startAS();
			 WebElement title=driver.findElementById("com.eisoo.anyshare:id/tv_comment_title_top");
			 int num1=num(title.getText());
			 System.out.println(num1);
			 if(num1==0)
			 { System.out.println("当前没有评论，请检查操作条件");
				 return false;}
			 WebElement comm = driver.findElementByAndroidUIAutomator("new UiSelector().description(\"comment\").instance(0)");
			 longPress(comm);
			 try
			 {
				 clickName("回复");
				 WebElement e1=driver.findElementById("com.eisoo.anyshare:id/et_commment");
				 longPress(e1);
				 e1.sendKeys();
				 clickName("发送");
			 }
			 catch (Exception e)
			 {
				 System.out.println("没有评论供删除/回复/拷贝");
			 }
			 startAS(); 
			 int num2=num(title.getText());
			 System.out.println(num2);
			 if(num1==num2-1)
				 return true;
			 else 
				 return false;
		  
	  }
	  
	  public int num(String name)
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
	  }
	  
	  public void longPress( WebElement e1) throws InterruptedException
	  { 
		  int x=e1.getLocation().x;
		  int y=e1.getLocation().y;
		  int width=e1.getSize().width;
		  int height=e1.getSize().height;
		//  System.out.println(x+width/2+" ,"+(y+height/2));
		  driver.swipe((x+width)/2, 500, (x+width)/2, 500, 2000);
		  Thread.sleep(1000);
	  }
 


  


  
  
}
