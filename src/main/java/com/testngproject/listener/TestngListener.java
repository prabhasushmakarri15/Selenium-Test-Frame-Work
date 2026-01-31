package com.testngproject.listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import com.testngproject.base.TestngpracticeBase;
import com.testngproject.utility.ExtentManager;
import com.testngproject.utility.RetryAnalyzer;

public class TestngListener implements ITestListener, IAnnotationTransformer {
	

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		ExtentManager.starttest(testName);
		ExtentManager.logstep("Test Started: " + testName);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		ExtentManager.logsucceswithscreenshot(TestngpracticeBase.getwebdriver(),"Test Passed Successfully!","Test End: " + testName + " - Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		ExtentManager.logfailureswithscreenshot(TestngpracticeBase.getwebdriver(),"Test Failed","Test End: " + testName + " - Test Failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onStart(ITestContext context) {
		ExtentManager.getReport();
	}

	@Override
	public void onFinish(ITestContext context) {
		ExtentManager.endtest();
	}

}
