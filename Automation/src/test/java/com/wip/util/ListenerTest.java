package com.wip.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerTest implements ITestListener
{
	//protected WebDriver driver;


	public static String filePath=System.getProperty("user.dir")+"\\test-output\\screenshots\\screenshot";
	FileInputStream fileInput =null;
	public void onTestStart(ITestResult result) 
	{
		// TODO Auto-generated method stub
		System.out.println(result.getName()+" test case started");
		
		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("The name of the testcase passed is :"+result.getName());
		
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		 if(ITestResult.FAILURE==result.getStatus())
		 {
			 	System.out.println("The name of the testcase failed is :"+result.getName());
			 	System.out.println("***** Error "+result.getName()+" test has failed *****");
		    	String methodName=result.getName().toString().trim();
		    	System.out.println(methodName);
		        ITestContext context = result.getTestContext();
		     
		 }
		 
		
	}
	


	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("The name of the testcase Skipped is :"+result.getName());
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
