package utils.supportlibraries;

import utils.core.FrameworkException;

public class DataFactory {

	private final String dataTablePath;
	private final String dataTableName;
	private String currentTestCase;

	public DataFactory(String dataTablePath, String dataTableName) {
		this.dataTablePath = dataTablePath;
		this.dataTableName = dataTableName;
	}

	private void checkPreRequisites() {
		if (this.currentTestCase == null) {
			throw new FrameworkException("DataFactory.currentTestCase is not set!");
		}
	}

	public void setCurrentRow(String currentTestCase) {
		this.currentTestCase = currentTestCase;
	}

	public String getData(String dataSheetName, String fieldName) {
		checkPreRequisites();
		ExcelDataAccess dataAccess = new ExcelDataAccess(this.dataTablePath, this.dataTableName);
		dataAccess.setDataSheetName(dataSheetName);

		int rowNum = dataAccess.getRowNum(this.currentTestCase, 0, 1);
		if (rowNum == -1) {
			throw new FrameworkException("The Test case \"" + this.currentTestCase
					+ "\" is not found in the test data sheet \"" + dataSheetName + "\"!");
		}
		return dataAccess.getValue(rowNum, fieldName);
	}

	public void putData(String dataSheetName, String fieldName, String dataValue) {
		checkPreRequisites();
		ExcelDataAccess dataAccess = new ExcelDataAccess(this.dataTablePath, this.dataTableName);
		dataAccess.setDataSheetName(dataSheetName);

		int rowNum = dataAccess.getRowNum(this.currentTestCase, 0, 1);
		if (rowNum == -1) {
			throw new FrameworkException("The Test case \"" + this.currentTestCase
					+ "\" is not found in the test data sheet \"" + dataSheetName + "\"!");
		}

		synchronized (DataFactory.class) {
			dataAccess.setValue(rowNum, fieldName, dataValue);
		}
	}
}
