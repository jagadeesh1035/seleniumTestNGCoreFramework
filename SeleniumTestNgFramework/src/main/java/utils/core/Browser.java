package utils.core;

public enum Browser {

	Chrome("chrome"), Firefox("firefox"), Edge("edge"), HtmlUnit("htmlunit"),InternetExplorer("internet explorer"), Opera("opera"),
	Chromium("chromium");

	private String value;

	private Browser(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
