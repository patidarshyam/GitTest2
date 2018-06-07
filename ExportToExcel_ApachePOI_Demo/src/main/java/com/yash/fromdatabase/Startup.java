package com.yash.fromdatabase;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class Startup {
	public static void main(String[] args) {
		
		GetDataFromDB getDataFromDB=new GetDataFromDB();
		List<User> users=getDataFromDB.getTableData();
		/*if(users != null && users.size() > 0){
			getDataFromDB.doExport(users);
			System.out.println("Success");
        }else{
            System.out.println("There is no data available in the table to export");
        }*/
		
		try {
		List<User> usersList=	getDataFromDB.importData("user_details.xls");
		System.out.println("Reading user==============="+usersList);
		for (User user : usersList) {
			
			System.out.println(user.getName());
			
		}
		
		} catch (InvalidFormatException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
