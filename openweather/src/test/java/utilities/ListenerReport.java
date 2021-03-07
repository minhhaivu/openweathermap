package utilities;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class ListenerReport implements ITestListener						
{		
	public void onTestStart(ITestResult result) 
	{
		System.out.println("New Test Started - " +result.getName());
	}
	
	public void onTestSuccess(ITestResult result) 
	{
		System.out.println("Test Successfully Finished! - " + result.getName());
	}
 
	public void onTestFailure(ITestResult result) 
	{
		System.out.println(result.getName() + "is Failed");
	}
}
