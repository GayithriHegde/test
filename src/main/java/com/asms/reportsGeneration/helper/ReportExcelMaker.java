package com.asms.reportsGeneration.helper;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asms.Exception.ExceptionHandler;

@Component
public class ReportExcelMaker {

	@Resource(name = "asmsProperties")
	private Properties properties;

	@Autowired
	private ExceptionHandler exceptionHandler;

	ResourceBundle messages;

	public void excelCurriculumMaker(String title, String subTitle, String[] columnNames, String[] columns) {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Curriculam Report");
		Object[][] rowValues = { { title }, { subTitle },

				columnNames, columns, };

		int rowNum = 0;

		for (Object[] rowValue : rowValues) {
			Row rows = sheet.createRow(rowNum++);

			int colNum = 0;
			for (Object field : rowValue) {

				Cell cell = rows.createCell(colNum++);
				if (field instanceof String) {
					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				}
			}

		}
		try {
			FileOutputStream outputStream = new FileOutputStream(properties.getProperty("report_file"));
			workbook.write(outputStream);
			workbook.close();

			File pdfFile = new File(properties.getProperty("report_file"));
			if (pdfFile.exists()) {

				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().open(pdfFile);
				} else {
					throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
							messages.getString("SYSTEM_EXCEPTION"));
				}

			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
						messages.getString("SYSTEM_EXCEPTION"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void excelMakerListOfUser(String title, String subTitle, String[] columnNames, String[] columns) {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("ListOfUser");
		Object[][] rowValues = { { title }, { subTitle },

				columnNames, columns, };

		int rowNum = 0;

		for (Object[] rowValue : rowValues) {
			Row rows = sheet.createRow(++rowNum);

			int colNum = 0;
			for (Object field : rowValue) {

				Cell cell = rows.createCell(++colNum);
				if (field instanceof String) {
					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				}
			}

		}
		try {
			FileOutputStream outputStream = new FileOutputStream(properties.getProperty("report_file1"));
			workbook.write(outputStream);
			workbook.close();

			File pdfFile = new File(properties.getProperty("report_file1"));
			if (pdfFile.exists()) {

				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().open(pdfFile);
				} else {
					throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
							messages.getString("SYSTEM_EXCEPTION"));
				}

			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
						messages.getString("SYSTEM_EXCEPTION"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Admission Excel Report Maker------------------
	// ----------------------------------------------
	public void excelMakerAdmission(String title, String subTitle, String[] columnNames, String[] columns) {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("AdmissionDetails");
		Object[][] rowValues = { { title }, { subTitle },

				columnNames, columns, };

		int rowNum = 0;

		for (Object[] rowValue : rowValues) {
			Row rows = sheet.createRow(++rowNum);
			//rows.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
			int colNum = 0;
			for (Object field : rowValue) {

				Cell cell = rows.createCell(++colNum);
				if (field instanceof String) {
					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				}
			}

		}
		try {
			FileOutputStream outputStream = new FileOutputStream(properties.getProperty("admission_report_file"));
			workbook.write(outputStream);
			workbook.close();

			File pdfFile = new File(properties.getProperty("admission_report_file"));
			if (pdfFile.exists()) {

				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().open(pdfFile);
				} else {
					throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
							messages.getString("SYSTEM_EXCEPTION"));
				}

			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
						messages.getString("SYSTEM_EXCEPTION"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
