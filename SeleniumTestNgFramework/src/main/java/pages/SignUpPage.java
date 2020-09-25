package pages;

import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;

import utils.supportlibraries.BasePage;
import utils.supportlibraries.ScriptHelper;

public class SignUpPage extends BasePage {

	public SignUpPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	By registrationTitle = By.xpath("//h1[@id='registration_title']");
	By hireFreeLancer = By.xpath("//div[text()='hire a freelancer']/parent::span");
	By workAsFreeLancer = By.xpath("//div[text()='work as a freelancer']/parent::span");
	By buttonSignUpWithEmail = By.xpath("//button[text()='Sign up with email']");

	By firstName = By.xpath("//input[@name='fname']");
	By lastName = By.xpath("//input[@name='lname']");
	By email = By.xpath("//input[@name='email']");
	By password = By.xpath("//input[@name='password']");
	By signUp = By.xpath("//button[text()='Sign Up']");
	By postSignUp = By.xpath("//div[@class='seller-states__container']");

	public SignUpPage selectWhatDoYouWantToDo() {
		waitUntilElementIsEnabled(registrationTitle, 10);
		if (dataTable.getData("LoginSuite", "WhatDoYouWantToDo").equalsIgnoreCase("hire")) {
			click(hireFreeLancer);
		} else if (dataTable.getData("LoginSuite", "WhatDoYouWantToDo").equalsIgnoreCase("work")) {
			click(workAsFreeLancer);
		}
		click(buttonSignUpWithEmail);
		return this;
	}

	public SignUpPage enterSignUpData() {
		inputText(firstName, dataTable.getData("LoginSuite", "FirstName"));
		inputText(lastName, dataTable.getData("LoginSuite", "LastName"));
		inputText(email, dataTable.getData("LoginSuite", "Email"));
		inputText(password, dataTable.getData("LoginSuite", "Password"));
		click(signUp);
		waitUntilElementIsEnabled(postSignUp, 10);
		return this;
	}

	public void verifySignUpSuccess() {
		if (objectExists(postSignUp)) {
			updateLogStatus(Status.PASS, "Sign up process is successfull");
		} else {
			updateLogStatus(Status.FAIL, "Sign up process is unsuccessfull");
		}
	}
}
