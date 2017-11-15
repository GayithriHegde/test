package com.asms.common.pdf;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asms.Exception.ExceptionHandler;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class PdfMaker {

	@Resource(name = "asmsProperties")
	private Properties properties;
	
	 @Autowired
	private ExceptionHandler exceptionHandler;
	  
	 ResourceBundle messages;
	

	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);

	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	
	
	private String title;
	private String subTitle;
	private String[] columns;
	private List<String> rows;
	
	//private String studentAdmissionNo;

	public void pdfMaker(String title, String subTitle,String[] columns, List<String> rows) {

		try {
			this.title = title;
			this.subTitle = subTitle;
			this.columns = columns;
			this.rows = rows;
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(properties.getProperty("pdf_file_path")));
			document.open();
			// addMetaData(document);
			// addTitlePage(document);
			addContent(document);
			
			File pdfFile = new File(properties.getProperty("pdf_file_path"));
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

			

			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	// Font.NORMAL, BaseColor.RED);
	// private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	// Font.BOLD);

	/*
	 * public void pdfCreator() { try { Document document = new Document();
	 * PdfWriter.getInstance(document, new
	 * FileOutputStream(properties.getProperty("pdf_file_path")));
	 * document.open(); // addMetaData(document); // addTitlePage(document);
	 * addContent(document); document.close(); } catch (Exception e) {
	 * e.printStackTrace(); } }
	 */

	private void addContent(Document document) throws DocumentException {
		Anchor anchor = new Anchor(title, catFont);
		anchor.setName(title);

		// Second parameter is the number of the chapter
		Chapter catPart = new Chapter(new Paragraph(anchor),0);

		Paragraph subPara = new Paragraph(subTitle, subFont);
		Section subCatPart = catPart.addSection(subPara);
		//subCatPart.add(new Paragraph(subTitle));

		// add a list
		// createList(subCatPart);
		Paragraph paragraph = new Paragraph();
		addEmptyLine(paragraph, 4);
		subCatPart.add(paragraph);

		// add a table
		createTable(subCatPart);

		// now add all this to the document
		document.add(catPart);
	}

	private void createTable(Section subCatPart) throws BadElementException {
		PdfPTable table = new PdfPTable(columns.length);
		PdfPCell cell = null;
		for (int i = 0; i < columns.length; i++) {
			cell = new PdfPCell(new Phrase(columns[i]));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
		}

		for (String s : rows) {

			table.addCell(s);
		}

		subCatPart.add(table);

	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

}
