package com.eisoo.anysharetest;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login extends Functions {
	  @BeforeMethod
	  public void beforeMethod() {
		  System.out.println("BeforeMethod");
	  }

	//  @AfterMethod
	  public void afterMethod() throws Exception {
		  System.out.println("afterMethod");
		  clearData();
	  }


	  @BeforeClass
	  public void beforeClass() throws Exception {
		  System.out.println("BeforeClass");
	  }

	//  @AfterClass
	  public void afterClass() {
		  System.out.println("AfterClass");
		  driver.quit();
	  }
	  /*
	@Test(groups="login")
	public void loginWrongPsd() throws Exception
	{
		 System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		assertFalse(loginTest("192.168.184.166","zfy","123124"),"错误密码不该登录");
	}
	
	 @Test(groups="login")
	public void loginIpTest() throws Exception
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		assertTrue(loginTest("192.168.184.166","zfy","123123"),"登录失败！");
	}
	*/
	  
	@Test(groups="login")
	public void loginDomainTest() throws Exception
	{	 
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		assertTrue(loginTest("116.236.224.243","zfy","123123"),"登录失败");
	}


}

