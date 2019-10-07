package com.bp.lib;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadDataFromExcel {

	/*public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ReadDataFromExcel newObject = new ReadDataFromExcel();
		ArrayList tcodes = new ArrayList();
		for (int sheetIndex = 0; sheetIndex <= 2; sheetIndex++) {
			tcodes = newObject.getDataFromExcelSheet(".\\testdata\\TCODES.xlsx", sheetIndex);
			for (int i = 0; i < tcodes.size(); i++) {
				System.out.println(tcodes.get(i));
			}
		}
	}*/

	public ArrayList<String> getDataFromExcelSheet(String filename, int sheetIndex) throws IOException {
		// Create an ArrayList to store the data read from excel sheet.
		//
		ArrayList<String> sheetData = new ArrayList();
		FileInputStream fis = null;
		try {
			//
			// Create a FileInputStream that will be use to read the
			// excel file.
			//
			fis = new FileInputStream(filename);

			//
			// Create an excel workbook from the file system.
			//
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			//
			// Get the first sheet on the workbook.
			//
			XSSFSheet sheet = workbook.getSheetAt(sheetIndex);

			//
			// When we have a sheet object in hand we can iterator on
			// each sheet's rows and on each row's cells. We store the
			// data read on an ArrayList so that we can printed the
			// content of the excel to the console.
			//
			Iterator rows = sheet.rowIterator();
			while (rows.hasNext()) {
				XSSFRow row = (XSSFRow) rows.next();
				Iterator cells = row.cellIterator();

				/*ArrayList<String> data = new ArrayList();
				while (cells.hasNext()) {
					XSSFCell cell = (XSSFCell) cells.next();
					data.add(cell.toString());
				}*/
				while(cells.hasNext())
				{
					XSSFCell cell = (XSSFCell) cells.next();
					sheetData.add(cell.toString());
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
		return sheetData;
	}

}
