package com.mastek.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mastek.bean.User;

public class UserDao {


	private String jdbcURL ="jdbc:oracle:thin:@localhost:1521:xe";
	private String jdbcUsername ="finalProject";
	private String jdbcPassword ="sys";
	private String jdbcDriver ="oracle.jdbc.driver.OracleDriver";
	
	private static final String INSERT_USERS_SQL = "INSERT INTO tbl_users"+"(first_name,last_name,mobileNumber,email,password,u_roles) VALUES (?,?,?,?,?,?)";
	
	private static final String SELECT_USER_BY_ID = "SELECT first_name,last_name,mobileNumber,email,password,u_roles from tbl_users where u_id = ?";
	private static final String SELECT_ALL_USERS = "SELECT * FROM tbl_users";
	private static final String DELETE_USERS_SQL = "DELETE FROM tbl_users WHERE u_id =?";
	private static final String UPDATE_USERS_SQL = "UPDATE tbl_users SET first_name = ?, last_name = ? , mobileNumber = ?,  email = ?, password = ?, u_roles = ?  where u_id =?";
	
	
	
public UserDao() {
		
	}
	
	
	protected Connection getConnection() {
		
		Connection connection = null;
		
		try {
			
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
		
	}
	
	

	// insert user
	
	public void insertUser(User user) throws  SQLException{
		
		System.out.println(INSERT_USERS_SQL);
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getMobileNumber());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.setString(6, user.getRole());
			System.out.println(preparedStatement);
			
		
			preparedStatement.executeUpdate();

		}catch(SQLException e) {
			printSQLException(e);
		};
	}
	
	
	
	
	
	
	
	
	
	
	private void printSQLException(SQLException ex) {

		for(Throwable e : ex) {
			if(e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLStete: "+ ((SQLException)e).getSQLState());
				System.err.println("Error Code: "+ ((SQLException)e).getErrorCode());
				System.err.println("Message: "+e.getMessage());
				Throwable t = ex.getCause();
				while(t != null) {
					System.out.println("Cause: "+ t);
					t = t.getCause();

				}

			}
		}
		
	}
	


	


}
