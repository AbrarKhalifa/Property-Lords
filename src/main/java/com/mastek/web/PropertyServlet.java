package com.mastek.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.mastek.bean.Property;
import com.mastek.bean.PropertyAddress;
import com.mastek.bean.PropertyDocument;
import com.mastek.bean.PropertyImage;
import com.mastek.bean.User;
import com.mastek.dao.PropertyAddressDAO;
import com.mastek.dao.PropertyDao;
import com.mastek.dao.PropertyDocDAO;
import com.mastek.dao.PropertyImgDAO;

/**
 * Servlet implementation class PropertyServlets
 */
@WebServlet("/PropertyServlet")
@MultipartConfig
public class PropertyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PropertyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		   HttpSession session = request.getSession();
		   User user = (User) session.getAttribute("userObj");
		
//		String price = request.getParameter("price");
//		
//		System.out.println("Price: " + price);
//		System.out.println(request.getParameter("price"));
//		User user = new User(1,"Abrar","Khalifa","9876958221","abrar@gmail.com","Abrar@1234","agent");
		Property property = new Property();
		int new_property=0;
		PropertyDao propertydao = new PropertyDao();
		property.setAgent_id_fk(user);
        property.setPropertyType(request.getParameter("propertyType"));
        property.setProSize(request.getParameter("propertySize"));
        property.setPrice(Double.parseDouble(request.getParameter("price")));
        property.setFeatures(request.getParameter("features"));
        property.setNoOfRooms(Integer.parseInt(request.getParameter("numRooms")));
        property.setNoOfKitchens(Integer.parseInt(request.getParameter("numKitchens")));
        property.setNoOfBathrooms(Integer.parseInt(request.getParameter("numBathrooms")));
        property.setAmenities(request.getParameter("amenities"));
        property.setStatus(request.getParameter("status"));
        property.setPurpose(request.getParameter("purpose"));
  
		new_property= propertydao.insertProperty(property);
		property.setPropertyId(new_property);
			
        System.out.println("new propety: "+property.getPropertyId());
        
        PropertyAddress address = new PropertyAddress();
        address.setProperty_id_fk(property);
        address.setCity(request.getParameter("city"));
        address.setLandmark(request.getParameter("landmark"));
        address.setSociety(request.getParameter("society"));
        address.setPincode(request.getParameter("pincode"));
        address.setState(request.getParameter("state"));
        
        PropertyAddressDAO property_DAO = new PropertyAddressDAO();
        property_DAO.insertProperty(address);
        
//        Part imagePart = request.getPart("propertyImages");
//        String fileName = saveImageToFileSystem(imagePart,"Images");
        
//        PropertyImage propertyImage = new PropertyImage();
//        propertyImage.setProperty_id_fk(new_property);
//        propertyImage.setImages(fileName);
//        
//        PropertyImgDAO propertyimg = new PropertyImgDAO();
//        propertyimg.insertPropertyImages(propertyImage);
//        
        
        Collection<Part> parts = request.getParts();
        List<String> img =  new ArrayList<>();
        for (Part part : parts) {
        	if (part.getName().equals("propertyImages")) {
        		String fileName = saveImageToFileSystem(part,"Images");
        		//System.out.println(fileName);
        		img.add(fileName);
        	}
        }
        //System.out.println(img);
        PropertyImage propertyImage = new PropertyImage();
        propertyImage.setProperty_id_fk(property);
        propertyImage.setImages(img);
        System.out.println(propertyImage.toString());
        PropertyImgDAO propertyimgdao = new PropertyImgDAO();
        propertyimgdao.insertPropertyImages(propertyImage);
        
        Part imagePart1 = request.getPart("propertyDocImages");
        String fileName1 = saveImageToFileSystem(imagePart1,"Docs");
        
        PropertyDocument propertydoc = new PropertyDocument();
        propertydoc.setProperty_id_fk(property);
        propertydoc.setDocumentImage(fileName1);
        
        PropertyDocDAO propertydocdao = new PropertyDocDAO();
        propertydocdao.insertPropertyDocs(propertydoc);
        
       
        //System.out.println(fileName);
        System.out.println(fileName1);
        //propertyImage.setImage(fileName);
        
        //property.setImages(propertyImage);
        
       
        
        //property.setAddresses(address);
        request.setAttribute("property", property);
        response.getWriter().print("<h3>Successfully Registered Property</h3>");
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
        
        }
	
	private String saveImageToFileSystem(Part filePart, String img) throws IOException {
		String uploadDirectory="";
		String fileName="";
		int count=1;
		if(img=="Images") {
			uploadDirectory = "D:\\MASTEK PROJECT\\Main Project Files\\MastekProjectG15\\src\\main\\webapp\\Property_images"; // Update this path
			fileName = "Property_IMG_"+System.currentTimeMillis() + "_" + filePart.getSubmittedFileName()+ count +getExtension(filePart); // Generate a unique filename
		}
		else if(img=="Docs") {
			uploadDirectory = "D:\\MASTEK PROJECT\\Main Project Files\\MastekProjectG15\\src\\main\\webapp\\Property_docs";
			fileName = "Property_DOC_" + System.currentTimeMillis() + "_" + filePart.getSubmittedFileName()+  + count +getExtension(filePart); // Generate a unique filename
		}
			try (InputStream inputStream = filePart.getInputStream();
             OutputStream outputStream = new FileOutputStream(uploadDirectory + File.separator + fileName)) {

            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
		
        count++;
        return uploadDirectory+"\\"+fileName;
    }
	
	private String getExtension(Part filePart) {
	    String contentDisposition = filePart.getHeader("content-disposition");
	    String[] tokens = contentDisposition.split(";");
	    for (String token : tokens) {
	        if (token.trim().startsWith("filename")) {
	            String fileName = token.substring(token.indexOf("=") + 2, token.length() - 1);
	            return fileName.substring(fileName.lastIndexOf("."));
	        }
	    }
	    return "";
	}



}
