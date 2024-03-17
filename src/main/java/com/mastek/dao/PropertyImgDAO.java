package com.mastek.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.mastek.bean.PropertyImage;

public class PropertyImgDAO {
	 public void insertPropertyImages(PropertyImage propertyImg) {
	        String query = "INSERT INTO TBL_PROPERTY_IMG (PROPERTY_ID_FK, IMG) VALUES (:1 , :2 )";
	        List<String> list = propertyImg.getImages();
	        System.out.println(list);
	        try (Connection connection = ConnectionManager.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	        	for(String imgUrl : list) {
	        		System.out.println("in for loop"+imgUrl);
	        		preparedStatement.setInt(1, propertyImg.getProperty_id_fk());
	        		preparedStatement.setString(2, imgUrl);

		            preparedStatement.executeUpdate();
	        	}


	            System.out.println("Success in Image");
	          	} catch (SQLException e) {
	            e.printStackTrace(); // Handle or log the exception as needed
	        }
		
	    }
}
