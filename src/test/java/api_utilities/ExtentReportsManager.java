package api_utilities;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportsManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;
	// method 1
	public void onStart(ITestContext testContext)
	{
		/*after maximum time run then generate the report current time and date
		 * SimpleDateFormat df =new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date d =new Date();
		String currentdatetimeStamp=df.format(d);*/
		 
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName ="Test-Report-" + timestamp + ".html";
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+"repName");// specify the current location of the report
		sparkReporter.config().setDocumentTitle("RestAssured API Report"); // Title of the report
		sparkReporter.config().setReportName("Petstore API Tetsing");// name of the report
		sparkReporter.config().setTheme(Theme.DARK);

		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application","Petstore");
		extent.setSystemInfo("Module","Admin");
		extent.setSystemInfo("Sub module","customer");
		extent.setSystemInfo("User name",System.getProperty("user.name"));
		extent.setSystemInfo("Environment","QA");

		String os=testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System",os);

		String browser=testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser",browser);

		List <String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups",includedGroups.toString());
		}
	}
	// method 2
	public void onTestsucces(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());// to display groups in report
		test.log(Status.PASS,result.getName()+" got successfully excuted");
	}
	// method 3
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());// to display groups in report
		test.log(Status.FAIL,result.getName()+" got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
//		try {
//			String imgPath=new BaseClass().captureScreen(result.getName());
//			test.addScreenCaptureFromPath(imgPath);
//		}
//		catch(IOException e1)
//		{
//			e1.printStackTrace();
//		}
		
	}
	// method 4
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());// to display groups in report
		test.log(Status.SKIP,result.getName()+" got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	// method 5
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
		
	}
	
	}

