package com.mastek.dao;
	
	
	import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mastek.bean.Property;

	
	public class PropertyDao {
		
		
		private String jdbcURL ="jdbc:oracle:thin:@localhost:1521:xe";
		private String jdbcUsername ="finalProject";
		private String jdbcPassword ="sys";
		private String jdbcDriver ="oracle.jdbc.driver.OracleDriver";
		
		private static final String INSERT_PROPERTY_SQL = "INSERT INTO tbl_properties"+"(u_id_fk, property_type, pro_size, price, features, no_of_rooms, no_of_kitchen, no_of_bathrooms, amenities, status, purpose) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
		private static final String SELECT_PROPERTY_BY_ID = "SELECT property_id, u_id_fk, property_type, pro_size, price, features, no_of_rooms, no_of_kitchen, no_of_bathrooms,  amenities, status, purpose  from tbl_properties where property_id = ?";
		private static final String SELECT_ALL_PROPERTY = "SELECT * FROM tbl_properties";
		private static final String DELETE_PROPERTY_SQL = "DELETE FROM tbl_properties WHERE property_id =?";
		private static final String UPDATE_PROPERTY_SQL = "UPDATE tbl_properties SET property_type = ? , pro_size = ? , price = ? , features = ? , no_of_rooms = ? , no_of_kitchen = ?, no_of_bathrooms = ?,  amenities = ? , status = ? , purpose= ? from tbl_properties where property_id =?";
		
		
			public PropertyDao() {
					
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
		
		public void insertProperty(Property property) throws  SQLException{
			
			System.out.println(INSERT_PROPERTY_SQL);
			try {
				Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROPERTY_SQL);
				preparedStatement.setInt(1, property.getAgent_id_fk());
				preparedStatement.setString(2, property.getPropertyType());
				preparedStatement.setString(3, property.getProSize());
				preparedStatement.setDouble(4, property.getPrice());
				preparedStatement.setString(5, property.getFeatures());
				preparedStatement.setInt(6, property.getNoOfRooms());
				preparedStatement.setInt(7, property.getNoOfKitchens());
				preparedStatement.setInt(8, property.getNoOfBathrooms());
				preparedStatement.setString(9, property.getAmenities());
				preparedStatement.setString(10, property.getStatus());
				preparedStatement.setString(11, property.getPurpose	());
	
	
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
