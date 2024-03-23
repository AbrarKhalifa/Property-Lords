package com.mastek.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mastek.bean.User;

public class UserDao {

	
	private static final String INSERT_USERS_SQL = "INSERT INTO tbl_users"+"(first_name,last_name,mobileNumber,email,password,u_roles) VALUES (?,?,?,?,?,?)";
	
	private static final String SELECT_USER_BY_ID = "SELECT first_name,last_name,mobileNumber,email,password,u_roles from tbl_users where u_id = ?";
	private final static String SELECT_ALL_USERS = "SELECT * FROM tbl_users";
	private static final String DELETE_USERS_SQL = "DELETE FROM tbl_users WHERE u_id =?";
	private static final String UPDATE_USERS_SQL = "UPDATE tbl_users SET first_name = ?, last_name = ? , mobileNumber = ?,  email = ?, password = ?, u_roles = ?  where u_id =?";
	
	
	
public UserDao() {
		
	}
	

	
	
	// select all user
		public static List<User> selectAllUser(){
			
			List<User> users = new ArrayList<>();
			
			try {
				Connection connection = ConnectionManager.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
				System.out.println(preparedStatement);
				
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next()) {
				
					    int userId = rs.getInt("u_id");
						String firstName = rs.getString("first_name");
					    String lastName = rs.getString("last_name");
					    String mobileNumber = rs.getString("mobileNumber");
					    String email = rs.getString("email");
					    String password = rs.getString("password");
					    String role = rs.getString("u_roles");
					users.add(new User(userId,firstName,lastName,mobileNumber,email,password,role));
				}

			}catch(SQLException e) {
				printSQLException(e);
			};
			return users;
		
		}
	

	// insert user
	
	public void insertUser(User user) throws  SQLException, NoSuchAlgorithmException{
		  
		
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getMobileNumber());
			preparedStatement.setString(4, user.getEmail());
			String hashedPassword = hashPassword(user.getPassword());
			preparedStatement.setString(5, hashedPassword);
			preparedStatement.setString(6, user.getRole());
			System.out.println(preparedStatement);
			
			preparedStatement.executeUpdate();

		}catch(SQLException e) {
			printSQLException(e);
		};
	}
	
	 
	    public static  String hashPassword(String password) throws NoSuchAlgorithmException {
	    	 final String SALT = "FixedSalt123!";// if two users having same password it has to store same hased password in database
	    	 password= password+SALT;
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hash = digest.digest(password.getBytes());
	        StringBuilder hexString = new StringBuilder();
	        for (byte b : hash) {
	            String hex = Integer.toHexString(0xff & b);
	            if (hex.length() == 1) hexString.append('0');
	            hexString.append(hex);
	        }
	        return hexString.toString();
	    }
	
	
	
	
	private static void printSQLException(SQLException ex) {

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
