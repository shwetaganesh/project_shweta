package com.bp.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperations 
{
	XSSFWorkbook wb;
	XSSFSheet sheet1;

	public ExcelOperations(String filepath) 
	{
		try 
		{
			File src = new File(filepath);
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);

		} 
		catch (Exception e) 
		{

			System.out.println(e.getMessage());
		}
	}

	public String getData(int sheet_index, int row_index, int col_index) 
	{
		
		return wb.getSheetAt(sheet_index).getRow(row_index).getCell(col_index).getStringCellValue();
	}
	
	public void setData(String sheetname,int rownum,int colnum,String result) throws IOException
	{
		sheet1 = wb.getSheet(sheetname);
		sheet1.getRow(rownum).getCell(colnum).setCellValue(result);
		try 
		{
			   FileOutputStream fos = new FileOutputStream("Test Data\\BP_User_Data.xls"); // saves xls file to                                                                                                             disk
			   wb.write(fos);
			   fos.close();  
		}
		catch (FileNotFoundException e) 
		{   
			   e.printStackTrace();
		} 
	}
}
