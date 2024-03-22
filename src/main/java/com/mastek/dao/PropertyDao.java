package com.mastek.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mastek.bean.Property;
import com.mastek.bean.PropertyAddress;
import com.mastek.bean.PropertyDocument;
import com.mastek.bean.PropertyImage;

import oracle.net.jdbc.TNSAddress.Address;

public class PropertyDao {
	
	private static final String INSERT_PROPERTY_SQL = "INSERT INTO tbl_properties"
			+ "(u_id_fk, property_type, pro_size, price, features, no_of_rooms, no_of_kitchen, no_of_bathrooms, amenities, status, purpose) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

//	// property_id, u_id_fk, property_type, pro_size, price, features, no_of_rooms,
//	// no_of_kitchen, no_of_bathrooms, amenities, status, purpose
//
//	private static final String SELECT_PROPERTY_BY_ID = "SELECT * from tbl_properties where property_id = ?";
//
//	// private static final String SELECT_ALL_PROPERTY = "SELECT * FROM
//	// tbl_properties";
//
//	private static final String DELETE_PROPERTY_SQL = "DELETE FROM tbl_properties WHERE property_id =?";
//	private static final String UPDATE_PROPERTY_SQL = "UPDATE tbl_properties SET property_type = ? , pro_size = ? , price = ? , features = ? , no_of_rooms = ? , no_of_kitchen = ?, no_of_bathrooms = ?,  amenities = ? , status = ? , purpose= ? from tbl_properties where property_id =?";
//	private static final String SELECT_IMAGES_BY_PROPERTY_ID = "SELECT IMG  from tbl_property_img where PROPERTY_ID_FK = ?";
//	private static final String SELECT_ADDRESS_BY_PROPERTY_ID = "SELECT SOCIETY,CITY,STATES,PINCODE  from tbl_property_add where PROPERTY_ID_FK = ?";

	
//	New 
//	
//    private static final String GET_PROPERTY_BY_ID_SQL = ""
//            + "SELECT p.*, a.*, d.* "
//            + "FROM Property p "
//            + "INNER JOIN PropertyAddress a ON p.address_id = a.addressId "
//            + "INNER JOIN PropertyDocument d ON p.document_id = d.documentId "
//            + "LEFT JOIN PropertyImage img ON p.propertyId = img.property_id "  // Left join for optional images
//            + "WHERE p.propertyId = ?";
//
//    private static final String GET_ALL_PROPERTIES_SQL = ""
//            + "SELECT p.*, a.*, d.*, img.url AS image_url "  // Select image URL from joined table
//            + "FROM Property p "
//            + "INNER JOIN PropertyAddress a ON p.address_id = a.addressId "
//            + "INNER JOIN PropertyDocument d ON p.document_id = d.documentId "
//            + "LEFT JOIN PropertyImage img ON p.propertyId = img.property_id";  // Left join for optional images
//
//    private static final String UPDATE_PROPERTY_SQL = ""
//            + "UPDATE Property SET owner_id = ?, propertyType = ?, proSize = ?, price = ?, features = ?, noOfRooms = ?, noOfKitchens = ?, noOfBathrooms = ?, amenities = ?, status = ?, purpose = ? "
//            + "WHERE propertyId = ?";

//    private static final String DELETE_PROPERTY_SQL = "DELETE FROM Property WHERE propertyId = ?";

    private static final String SAVE_PROPERTY_IMAGE_SQL = "INSERT INTO tbl_property_img (property_id_fk, img) VALUES (?, ?)";

    private static final String SAVE_PROPERTY_DOC_SQL = "INSERT INTO tbl_property_doc (property_id_fk, pro_doc_img) VALUES (?, ?)";
	
	private static final String querysel = "select PROPERTY_ID from TBL_PROPERTIES where PROPERTY_ID=(select max(PROPERTY_ID) from tbl_properties)";

// End	
	
	
	
	
	Connection connection = null;

	
	public PropertyDao() throws SQLException {
		
        connection = ConnectionManager.getConnection();

	}

	// insert user

	public void insertProperty(Property property) {
		
		
		//int property_new = 0;


		try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROPERTY_SQL)) {

			preparedStatement.setInt(1, property.getOwner().getUserId()); // Set owner ID as foreign key
			preparedStatement.setString(2, property.getPropertyType());
			preparedStatement.setString(3, property.getProSize());
			preparedStatement.setDouble(4, property.getPrice());
			preparedStatement.setString(5, property.getFeatures());
			preparedStatement.setInt(6, property.getNoOfRooms());
			preparedStatement.setInt(7, property.getNoOfKitchens());
			preparedStatement.setInt(8, property.getNoOfBathrooms());
			preparedStatement.setString(9, property.getAmenities());
			preparedStatement.setString(10, property.getStatus());
			preparedStatement.setString(11, property.getPurpose());

			preparedStatement.executeUpdate();
			 // Save address and document after getting the auto-generated property ID
            int propertyId = getGeneratedKey(connection);
            
            System.out.println("propertyId "+propertyId);
            PropertyAddressDAO addressDao = new PropertyAddressDAO();
            addressDao.insertProperty(propertyId,property.getAddress());
            
            
            PropertyDocDAO docDao = new PropertyDocDAO();
            docDao.insertPropertyDocs(propertyId,property.getDocument());
            
            int finalPropertyId = propertyId;
            // Save each property image
            PropertyImgDAO imageDao = new PropertyImgDAO();
            for (PropertyImage image : property.getImages()) {
                imageDao.insertPropertyImages(finalPropertyId,image);
            }
            
          
         
        

		 } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
		//return property_new;

	}
	

	
	private int getGeneratedKey(Connection conn) throws SQLException {
        int generatedKey = -1;
        ResultSet rs = conn.createStatement().executeQuery(querysel);
        if (rs.next()) {
            generatedKey = rs.getInt(1);
        }
        return generatedKey;
    }

}
