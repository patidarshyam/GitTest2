package com.yash.fromdatabase;

import java.sql.*;
//import org.apache.log4j.Logger;

/**
 * This class will perform operation related to DB like connection, disconnect,
 * providing preparedStatement object and ResultSet object. This class will be
 * responsible to have transaction complete operation as well like closing
 * connection, PreparedStatement object etc.
 * 
 * @author shyam.patidar
 *
 */
public class DBUtil {

	//private static Logger logger = Logger.getLogger(DBUtil.class);
	private static String driverClassName = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost/poc";
	private static String user = "root";
	private static String password = "root";
	private static Connection connection = null;
	public static PreparedStatement preparedStatement = null;

	/**
	 * Driver Should be loaded whenever DBUtil is called
	 */
	static {
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		//	logger.error("ERROR : " + e.getMessage());
		}
	}

	/**
	 * Create connection Object
	 */
	public static Connection connect() {
		try {
			connection = DriverManager.getConnection(url, user, password);
		//	logger.info("con object : " + connection);
		} catch (SQLException e) {
			e.printStackTrace();
		//	logger.error("ERROR : " + e.getMessage());
		}
		return connection;
	}

	/**
	 * This method will return the PreparedStatement object based on the sql
	 * provided. This method should have call for connection because when you
	 * need transaction, that time you will require connection object.
	 * 
	 * @param sql
	 *            is any dml query
	 * @return
	 */
	public static PreparedStatement createPreparedstatement(String sql) {
		connect();
		try {
			preparedStatement = connection.prepareStatement(sql);
		//	logger.info(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		//	logger.error("ERROR : " + e.getMessage());
		}
		return preparedStatement;
	}

	/**
	 * This is closing the Connection object
	 */
	public static void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		//	logger.error("ERROR : " + e.getMessage());
		}
	}

	/**
	 * This is closing the PreparedStatement object
	 */
	public static void closePreparedStatement() {
		try {
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		//	logger.error("ERROR : " + e.getMessage());
		}
	}
}
