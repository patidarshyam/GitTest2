package com.yash.fromdatabase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class GetDataFromDB {

	public List<User> getTableData() {

		PreparedStatement preparedStatement = null;
		List<User> users = new ArrayList<User>();
		try {
			preparedStatement = DBUtil.createPreparedstatement("SELECT * FROM users");
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setContact(rs.getString("contact"));
				user.setEmail(rs.getString("email"));
				user.setRegistrationId(rs.getString("registrationId"));
				users.add(user);

			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBUtil.closePreparedStatement();
			DBUtil.closeConnection();
		}
		return users;
	}

	/**
	 * Writing data to excel file from database
	 * 
	 * @param users
	 */
	public void doExport(List<User> users) {
		if (users != null && !users.isEmpty()) {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet();
			HSSFRow headingRow = sheet.createRow(0);

			headingRow.createCell((short) 0).setCellValue("ID");
			headingRow.createCell((short) 1).setCellValue("Name");
			headingRow.createCell((short) 2).setCellValue("Contact");
			headingRow.createCell((short) 3).setCellValue("Email");
			headingRow.createCell((short) 4).setCellValue("Registration ID");

			short rowNo = 1;

			for (User user : users) {

				HSSFRow row = sheet.createRow(rowNo);

				row.createCell((short) 0).setCellValue(user.getId());
				row.createCell((short) 1).setCellValue(user.getName());
				row.createCell((short) 2).setCellValue(user.getContact());
				row.createCell((short) 3).setCellValue(user.getEmail());
				row.createCell((short) 4).setCellValue(user.getRegistrationId());
				rowNo++;

			}

			FileOutputStream fileOut;
			try {
				fileOut = new FileOutputStream("user_details.xls");
				workbook.write(fileOut);
				fileOut.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("Invalid directory or file not found");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error occurred while writting excel file to directory");
			}

		}
	}

	/**
	 * Reading data from excel file
	 * 
	 * @param fileName
	 * @return
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public List<User> importData(String fileName) throws InvalidFormatException, IOException {
		List<User> users;

		// Create Workbook from Existing File
		InputStream fileIn = new FileInputStream(fileName);
		Workbook wb = WorkbookFactory.create(fileIn);
		Sheet sheet = wb.getSheetAt(0);

		// Define Data & Row Array and adjust from Zero Base Number
		users = new ArrayList<User>();

		// Create a DataFormatter to format and get each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();

		// 2. Or you can use a for-each loop to iterate over the rows and
		// columns
		System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
		int i = 0;
		for (Row row : sheet) {
			if (row.getRowNum() != 0) {
			User user = new User();
			for (Cell cell : row) {
				System.out.println("Row===>" + row.getRowNum() + "Cell----> " + cell.getColumnIndex());
				
					switch (cell.getColumnIndex()) {
					case 0:
						user.setId(Integer.parseInt(dataFormatter.formatCellValue(cell)));
						break;
					case 1:
						user.setName(dataFormatter.formatCellValue(cell));
						break;
					case 2:
						user.setContact(dataFormatter.formatCellValue(cell));
						break;
					case 3:
						user.setEmail(dataFormatter.formatCellValue(cell));
						break;
					case 4:
						user.setRegistrationId(dataFormatter.formatCellValue(cell));
						break;

					default:
						break;
					}
					String cellValue = dataFormatter.formatCellValue(cell);
					System.out.print(cellValue + "\t");
				

				System.out.println();
			}
			
			users.add(user);
			}
		}

		return users;
	}

}
