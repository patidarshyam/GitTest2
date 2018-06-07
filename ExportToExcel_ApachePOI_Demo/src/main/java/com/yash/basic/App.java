package com.yash.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class App {
	public static void main(String[] args) throws IOException, InvalidFormatException {
		// Create 2D Array of Data
		double[][] value = new double[5][5];

		for (int i = 0; i < value.length; i++)
			for (int j = 0; j < value[i].length; j++)
				value[i][j] = i + j;

		// Export Data to Excel File
		exportData("data.xls", "i+j", value);

		// Import Data from Excel File
		double[][] data = importData("data.xls", 0);

		// Display Data from File
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++)
				System.out.print(data[i][j] + "\t");

			System.out.println();
		}

	}

	public static void exportData(String fileName, String tabName, double[][] data)
			throws FileNotFoundException, IOException {
		// Create new workbook and tab
		Workbook wb = new HSSFWorkbook();
		FileOutputStream fileOut = new FileOutputStream(fileName);
		Sheet sheet = wb.createSheet(tabName);

		// Create 2D Cell Array
		Row[] row = new Row[data.length];
		Cell[][] cell = new Cell[row.length][];

		// Define and Assign Cell Data from Given
		for (int i = 0; i < row.length; i++) {
			row[i] = sheet.createRow(i);
			cell[i] = new Cell[data[i].length];

			for (int j = 0; j < cell[i].length; j++) {
				cell[i][j] = row[i].createCell(j);
				cell[i][j].setCellValue(data[i][j]);
			}

		}

		// Export Data
		wb.write(fileOut);
		fileOut.close();

	}

	public static double[][] importData(String fileName, int tabNumber)
			throws FileNotFoundException, IOException, InvalidFormatException {

		double[][] data;

		// Create Workbook from Existing File
		InputStream fileIn = new FileInputStream(fileName);
		Workbook wb = WorkbookFactory.create(fileIn);
		Sheet sheet = wb.getSheetAt(tabNumber);

		// Define Data & Row Array and adjust from Zero Base Numer
		data = new double[sheet.getLastRowNum() + 1][];
		Row[] row = new Row[sheet.getLastRowNum() + 1];
		Cell[][] cell = new Cell[row.length][];

		// Transfer Cell Data to Local Variable
		for (int i = 0; i < row.length; i++) {
			row[i] = sheet.getRow(i);

			// Note that cell number is not Zero Based
			cell[i] = new Cell[row[i].getLastCellNum()];
			data[i] = new double[row[i].getLastCellNum()];

			for (int j = 0; j < cell[i].length; j++) {
				cell[i][j] = row[i].getCell(j);
				data[i][j] = cell[i][j].getNumericCellValue();
			}

		}

		fileIn.close();
		return data;
	}
}
