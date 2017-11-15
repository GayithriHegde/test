package com.asms.common.excel;

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
public class ExcelMaker {
	
	  
	  @Resource(name = "asmsProperties")
	  private Properties properties;
	  
	  @Autowired
	  private ExceptionHandler exceptionHandler;
	  
	  ResourceBundle messages;
	
   
 public void excelMaker(String title, String subTitle,String[] columnNames, String[] columns) {
  
         XSSFWorkbook workbook = new XSSFWorkbook();
         XSSFSheet sheet = workbook.createSheet("timeTable");
         Object[][] rowValues = { 
           {title},
           { subTitle },
           
           columnNames,
                 columns,
         };

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
             FileOutputStream outputStream = new FileOutputStream(properties.getProperty("excel_file_path"));
             workbook.write(outputStream);
             workbook.close();
             
             File pdfFile = new File(properties.getProperty("excel_file_path"));
    if (pdfFile.exists()) {

     if (Desktop.isDesktopSupported()) {
      Desktop.getDesktop().open(pdfFile);
     } 
      else {
       throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
         messages.getString("SYSTEM_EXCEPTION"));
      }
     

    } else {
     throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
       messages.getString("SYSTEM_EXCEPTION"));
    }


   
         }
         catch (Exception e) {
    e.printStackTrace();
   }

     }

}