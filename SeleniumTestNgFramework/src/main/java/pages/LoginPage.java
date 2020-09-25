package pages;

import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;

import utils.supportlibraries.BasePage;
import utils.supportlibraries.ScriptHelper;

public class LoginPage extends BasePage{
	ScriptHelper scriptHelper;
	
	public LoginPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
		this.scriptHelper=scriptHelper;
	}

	By email = By.xpath("//input[@name='email']");
	By password = By.xpath("//input[@name='password']");
	By logInButton = By.xpath("//button[text()='Log In']");
	By postLogin = By.xpath("//div[@class='seller-states__container']");
	
	
	public void loginToApplication() {
		waitUntilElementIsClickable(email, 10);
		inputText(email, dataTable.getData("LoginSuite", "Email"));
		inputText(password, dataTable.getData("LoginSuite", "Password"));
		click(logInButton);
		waitUntilElementIsEnabled(postLogin, 20);
		
		if(objectExists(postLogin)) {
			updateLogStatus(Status.PASS, "Login Successfull. Application is navigated to Hirepage");
		}else {
			updateLogStatus(Status.PASS, "Login not successfull. Application is navigated to Hirepage");
		}
	}
	
}
