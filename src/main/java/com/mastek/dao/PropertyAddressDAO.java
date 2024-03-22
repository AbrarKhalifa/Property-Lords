package com.mastek.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mastek.bean.PropertyAddress;

public class PropertyAddressDAO {
	 public void insertProperty(int property_id_fk,PropertyAddress propertyAddress) {
	        String query = "INSERT INTO TBL_PROPERTY_ADD (PROPERTY_ID_FK, LANDMARK, SOCIETY, CITY, STATES, PINCODE) VALUES (:1 , :2 , :3 , :4 , :5 , :6 )";
	        
	        try (Connection connection = ConnectionManager.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	            preparedStatement.setInt(1, property_id_fk);
	            preparedStatement.setString(2, propertyAddress.getLandmark());
	            preparedStatement.setString(3, propertyAddress.getSociety());
	            preparedStatement.setString(4, propertyAddress.getCity());
	            preparedStatement.setString(5, propertyAddress.getState());
	            preparedStatement.setString(6, propertyAddress.getPincode());
	            

	            preparedStatement.executeUpdate();

	            System.out.println("Success in Address");
	          	} catch (SQLException e) {
	            e.printStackTrace(); // Handle or log the exception as needed
	        }
		
	    }

}
