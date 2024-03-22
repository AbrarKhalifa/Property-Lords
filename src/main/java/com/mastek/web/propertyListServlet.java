package com.mastek.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mastek.bean.Property;
import com.mastek.dao.ConnectionManager;

import oracle.jdbc.OracleTypes;

/**
 * Servlet implementation class propertyListServlet
 */
@WebServlet("/propertyListServlet")
public class propertyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public propertyListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*
	 * response.setContentType("text/html"); PrintWriter out = response.getWriter();
	 * 
	 * try { Connection conn = ConnectionManager.getConnection(); CallableStatement
	 * cs = conn.prepareCall("{call displayPropertyList(?)}"); // Register the OUT
	 * parameter for the cursor cs.registerOutParameter(1, OracleTypes.CURSOR);
	 * 
	 * // Execute the stored procedure cs.execute();
	 * 
	 * ResultSet rs = (ResultSet)cs.getObject(1); // Store the data in a list of
	 * Property objects List<Property> properties = new ArrayList<>(); while
	 * (rs.next()) {
	 * 
	 * 
	 * Property property = new Property();
	 * property.setPropertyId(rs.getInt("property_id"));
	 * property.setPropertyType(rs.getString("property_type"));
	 * property.setProSize(rs.getString("pro_size"));
	 * property.setPurpose(rs.getString("purpose"));
	 * property.setPrice(rs.getDouble("price"));
	 * property.setNoOfRooms(rs.getInt("no_of_rooms"));
	 * property.setNoOfKitchens(rs.getInt("no_of_kitchen"));
	 * property.setNoOfBathrooms(rs.getInt("no_of_bathrooms"));
	 * property.setAddress(rs.getString("address"));
	 * 
	 * String filePath = rs.getString("img");
	 * 
	 * 
	 * // Get the index of the last occurrence of the directory separator (\) int
	 * lastIndexOfSeparator = filePath.lastIndexOf('\\');
	 * 
	 * 
	 * // Get the substring starting from the last occurrence of the directory
	 * separator String newPath = filePath.substring(lastIndexOfSeparator + 1);
	 * 
	 * // Replace backslashes with double backslashes newPath =
	 * newPath.replaceAll("\\\\", "\\\\\\\\");
	 * 
	 * // Add the necessary directory (Property_images\\) to the beginning of the
	 * path newPath = "Property_images\\" + newPath;
	 * 
	 * System.out.println(newPath);
	 * 
	 * property.setImageData(newPath); //property.setImageData(rs.getString("img"));
	 * 
	 * property.setImageData(rs.getString("img")); // Set image data as string
	 * 
	 * // Retrieve image data from the database InputStream imageStream =
	 * rs.getBinaryStream("img"); if (imageStream != null) { byte[] imageData =
	 * imageStream.readAllBytes(); property.setImageData(imageData); }
	 * 
	 * properties.add(property); }
	 * 
	 * // Close resources rs.close(); cs.close(); conn.close();
	 * 
	 * // Set the list as an attribute in the request object
	 * request.setAttribute("properties", properties);
	 * request.getRequestDispatcher("property-list.jsp").forward(request, response);
	 * 
	 * 
	 * // Forward the request to the JSP } catch (SQLException e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * }
	 */
		
		  List<Property> properties = new ArrayList<>();

	        try {
	            Connection conn = ConnectionManager.getConnection();
	            CallableStatement cs = conn.prepareCall("{call displayPropertyList(?)}");
	            cs.registerOutParameter(1, OracleTypes.CURSOR);
	            cs.execute();
	            ResultSet rs = (ResultSet)cs.getObject(1);

	            while (rs.next()) {
	                Property property = new Property();
	                property.setPropertyId(rs.getInt("property_id"));
	                property.setPropertyType(rs.getString("property_type"));
	                property.setProSize(rs.getString("pro_size"));
	                property.setPurpose(rs.getString("purpose"));
	                property.setPrice(rs.getDouble("price"));
	                property.setNoOfRooms(rs.getInt("no_of_rooms"));
	                property.setNoOfKitchens(rs.getInt("no_of_kitchen"));
	                property.setNoOfBathrooms(rs.getInt("no_of_bathrooms"));
	                property.setAddress(rs.getString("address"));

	                
	                String filePath = rs.getString("img");
	                int lastIndexOfSeparator = filePath.lastIndexOf('\\');
	                String newPath = filePath.substring(lastIndexOfSeparator + 1).replaceAll("\\\\", "\\\\\\\\");
	                newPath = "Property_images\\" + newPath;
	                property.setImageData(newPath);
	                properties.add(property);
	            }

	            rs.close();
	            cs.close();
	            conn.close();

	            // Convert fetched data to JSON
	            String jsonData = new Gson().toJson(properties);

	            // Set content type and write JSON response
	            response.setContentType("application/json");
	            response.setCharacterEncoding("UTF-8");
	            PrintWriter out = response.getWriter();
	            out.print(jsonData);
	            out.flush();

	        } catch (SQLException e) {
	            e.printStackTrace();
	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	            response.getWriter().write("Error occurred while fetching data from the database.");
	        }
	    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
