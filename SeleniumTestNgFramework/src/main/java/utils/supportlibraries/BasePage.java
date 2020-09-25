package utils.supportlibraries;

public class BasePage extends WebDriverUtils {

	protected static ScriptHelper scriptHelper;

	public BasePage(ScriptHelper scriptHelper) {
		super(scriptHelper);
		BasePage.scriptHelper = scriptHelper;
	}

}
