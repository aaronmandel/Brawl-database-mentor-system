package BrawlhallaClinic;

import java.sql.*;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class DatabaseManager {
	
	static Connection conn = null;
	static String url = "jdbc:mysql://localhost/Brawlhalla"; //establishes the connection to the SQL Table

	public static Connection getConnection() {
		
		if (conn == null) {
			try {
			conn = DriverManager.getConnection(url, "root", "Redw00d92"); //File name, password
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	
	}
		
		try {
			if (!conn.isValid(1000)) {
				conn = DriverManager.getConnection(url, "root", "Redw00d92");  //File name, password
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	return conn;
}

	public static void add(String[] characters) {
		// TODO Auto-generated method stub
		
	}

	public static void setSize(int i, int j) {
		// TODO Auto-generated method stub
		
	}

	public static void show() {
		// TODO Auto-generated method stub
		
	}
	
	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException { //Builds the table layout
		 

	ResultSetMetaData metaData = rs.getMetaData(); 
	 

	// names of columns 
	    Vector<String> columnNames = new Vector<String>(); 
	    int columnCount = metaData.getColumnCount(); 
	    for (int column = 1; column <= columnCount; column++) { 
	        columnNames.add(metaData.getColumnName(column)); 
	    } 
	 

	// data of the table 
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>(); 
	    while (rs.next()) { 
	        Vector<Object> vector = new Vector<Object>(); 
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) { 
	            vector.add(rs.getObject(columnIndex)); 
	        } 
	        data.add(vector); 
	    } 
	 

	return new DefaultTableModel(data, columnNames); 
	 

	} 
}
