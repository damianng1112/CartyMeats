package model;

import java.sql.*;


public enum EmployeeDAO {
	instance;
	static Connection con = null;
	static Statement stmt = null;
	static ResultSet rs = null;
		
	final private String host ="127.0.0.1:3306";
	final private String user = "root";
	final private String password = "";
		
	public Connection getConnection() throws Exception {
		
		//Load MySQL Driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		//Setup the connection with the DB
		con = DriverManager.getConnection("jdbc:mysql://" + host + "/cartymeats?" + "user=" + user + "&password=" + password);
		return con;

	}
	
	public void registerEmployee(Employee employee) throws Exception {
        
        Connection con = getConnection();

		//Create the MySQL statement for inserting the employee
        try {
        	PreparedStatement pstmt = con.prepareStatement("INSERT INTO users" +
            "  (fullname, username, password, email, contact) VALUES " +
            " (?, ?, ?, ?, ?);");
        	
        	pstmt.setString(1, employee.getFullname());
        	pstmt.setString(2, employee.getUsername());
        	pstmt.setString(3, employee.getPassword());
        	pstmt.setString(4, employee.getEmail());
        	pstmt.setString(5, employee.getContact());

            // Step 3: Execute the query or update query
            pstmt.executeUpdate();

        } catch (Exception e) {
            // process sql exception
        	e.printStackTrace();
        }
    }
	
	public Employee loginEmployee(String username, String password) throws Exception {
	    Connection con = getConnection();
	    Employee employee = null;
	    
	    try {
	        PreparedStatement pstmt = con.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
	        pstmt.setString(1, username);
	        pstmt.setString(2, password);
	        
	        ResultSet rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            // Employee found, create a new Employees object with the retrieved data
	            employee = new Employee();
	            employee.setFullname(rs.getString("fullname"));
	            employee.setUsername(rs.getString("username"));
	            employee.setPassword(rs.getString("password"));
	            employee.setEmail(rs.getString("email"));
	            employee.setContact(rs.getString("contact"));
	        }
	    } catch (SQLException e) {
	        // Handle any SQL errors
	        e.printStackTrace();
	    }
	    
	    return employee;
	}

	public void updateEmployee(Employee employee, String username) throws Exception {
	    Connection con = getConnection();
	    
	    try {
	        PreparedStatement pstmt = con.prepareStatement("UPDATE users SET fullname=?, username=?, password=?, email=?, contact=? WHERE username=?");
	        pstmt.setString(1, employee.getFullname());
	        pstmt.setString(2, employee.getUsername());
	        pstmt.setString(3, employee.getPassword());
	        pstmt.setString(4, employee.getEmail());
	        pstmt.setString(5, employee.getContact());
	        pstmt.setString(6, username);
	        
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        // Handle any SQL errors
	        e.printStackTrace();
	    }
	}

	public void deleteEmployee(String username) throws Exception {
	    Connection con = getConnection();
	    
	    try {
	        PreparedStatement pstmt = con.prepareStatement("DELETE FROM users WHERE username=?");
	        pstmt.setString(1, username);
	        
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        // Handle any SQL errors
	        e.printStackTrace();
	    }
	}

	
}
