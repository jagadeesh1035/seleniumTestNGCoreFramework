package pages;

import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;

import utils.supportlibraries.BasePage;
import utils.supportlibraries.ScriptHelper;

public class HomePage extends BasePage{
	ScriptHelper scriptHelper;

	public HomePage(ScriptHelper scriptHelper) {
		super(scriptHelper);
		this.scriptHelper = scriptHelper;
	}

	By logo = By.xpath("//div[contains(@class,'logo__wrapper')]/a");
	By inputFindExpert = By.xpath("//div[contains(@class,'find-expert')]/input");
	By buttonFindExpert = By.xpath("//div[contains(@class,'find-expert')]/a");
	By linkLogIn = By.xpath("//a[@title='Log in']");
	By linkRegister = By.xpath("//a[contains(@title,'register')]");
	
	
	public HomePage launchApplication() {
		String applicationUrl = properties.getProperty("ApplicationURL");
		updateLogStatus(Status.INFO, "Launching the application::["+applicationUrl+"]");
		driver.get(applicationUrl);
		waitUntilElementIsEnabled(logo, 20);
		if (objectExists(logo)) {
			updateLogStatus(Status.PASS, "Application is successfully launched");
		} else {
			updateLogStatus(Status.FAIL, "Application is not launched");
		}
		return this;
	}
	
	
	public SearchResultPage searchForASkill() {
		inputText(inputFindExpert, dataTable.getData("LoginSuite", "SkillToSearch"));
		click(buttonFindExpert);
		return new SearchResultPage(scriptHelper);
	}
	public LoginPage clickLogin() {
		click(linkLogIn);
		return new LoginPage(scriptHelper);
	}
	public SignUpPage clickSignUp() {
		click(linkRegister);
		return new SignUpPage(scriptHelper);
	}
	
}
