package seniorproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {
	final static String url = "jdbc:mysql://database-2.cgeho1qoscbs.us-east-1.rds.amazonaws.com:3306/myDB2";
	final static String username = "admin";
	final static String password = "49Ug2pJ1OcxGrbvDdDxE";
	
	public static Connection connect(Connection connection) {
		try {
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connected to database. Connection: " + connection);
			/*
			// Test for updating Employee table
			String sql = "INSERT INTO Employee (number, name, username, password) VALUES (?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, "6");
			statement.setString(2, "Sam Jackson");
			statement.setString(3, "Jacksons");
			statement.setString(4, "123456789");
			int rows = statement.executeUpdate();
			if (rows > 0) {
				System.out.println("Row inserted");
			}
			*/
			
		} catch (SQLException e) {
			System.out.println("Database connection error");
			e.printStackTrace();
		}
		return connection;
	}
			
	public static void disconnect(Connection connection) {
		if (connection != null) {
	        try {
	            connection.close();
	            System.out.println("Database connection closed");
		    } catch (SQLException e) { 
		        System.out.println("Database disconnection error");
		    }
		}
		if (connection == null) {
			System.out.println("Cannot close, connection == " + connection);
		}
	}
}