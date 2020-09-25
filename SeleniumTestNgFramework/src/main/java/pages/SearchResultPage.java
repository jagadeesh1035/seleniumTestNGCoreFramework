package pages;

import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;

import utils.supportlibraries.BasePage;
import utils.supportlibraries.ScriptHelper;

public class SearchResultPage extends BasePage{

	public SearchResultPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}
	
	
	
	By searchHeader = By.xpath("//span[text()='Curated Offers']");
	By searchCount = By.xpath("//span[text()='Curated Offers']/following-sibling::span");
	
	
	public boolean findOutSearchResultsDisplayed() {
		if (objectExists(searchHeader)) {
			updateLogStatus(Status.PASS, "Search action is success and given some results");
			return true;
		} else {
			updateLogStatus(Status.FAIL, "Search action is not success");
			return false;
		}
	}

	public void getSearchResults() {
		if (findOutSearchResultsDisplayed()) {
			updateLogStatus(Status.INFO, "The application has returned" + findElement(searchCount).getText());
		}
	}
	
}
