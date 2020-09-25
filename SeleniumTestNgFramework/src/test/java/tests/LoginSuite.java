package tests;

import org.testng.annotations.Test;

import pages.HomePage;
import utils.supportlibraries.BaseTest;

public class LoginSuite extends BaseTest {

	@Test(description = "Test case to search for a skill on the home page")
	public void testCaseSearchForSkill() {
		HomePage home = new HomePage(scriptHelper);
		home.launchApplication().searchForASkill().getSearchResults();
	}

	@Test(description = "To login to the application")
	public void loginToTheApplication() {
		HomePage home = new HomePage(scriptHelper);
		home.launchApplication().clickLogin().loginToApplication();
	}

	@Test(description = "To verify the signup functionality of the application")
	public void signUpToTheApplication() {
		HomePage home = new HomePage(scriptHelper);
		home.launchApplication().clickSignUp().selectWhatDoYouWantToDo().enterSignUpData().verifySignUpSuccess();

	}
}
