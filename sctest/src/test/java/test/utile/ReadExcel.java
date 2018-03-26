package test.utile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	public ReadExcel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String readFromExcel(String fileName, String sheetName, String pathFile, int row, int col) throws IOException{
		File file = new File(pathFile);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook book = null;
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if(fileExtensionName.equals(".xlsx")){
			book = new XSSFWorkbook(inputStream);
		}else if(fileExtensionName.equals(".xls")){
			book = new HSSFWorkbook(inputStream);
		}
		Sheet sheet = book.getSheet(sheetName);
		Row ligne = null;
		if(row==sheet.getLastRowNum()){
			ligne = sheet.getRow(sheet.getLastRowNum());
		}else{
			ligne = sheet.getRow(row);
		}
		
		
		return ligne.getCell(col).getStringCellValue();
	}

}
