package com.mastek.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mastek.bean.User;
import com.mastek.dao.ConnectionManager;
import com.mastek.dao.UserDao;



/**
 * Servlet implementation class LoginRegisterServlet
 */
@WebServlet("/LoginRegisterServlet")
public class LoginRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDao userDao;

	
    public LoginRegisterServlet() throws ServletException{
		userDao = new UserDao();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 String action = request.getParameter("action");

	        if (action.equals("login")) {
	        	
	        	String email = request.getParameter("email");
	        	
	        	String password = request.getParameter("password");
	        	
	        	

	        	String query = "SELECT * FROM tbl_users WHERE email = ? and password =?";

	        	try {
	        	    
	        	    Connection connection = ConnectionManager.getConnection();
	        	    PreparedStatement  preparedStatement = connection.prepareStatement(query);
	        	    preparedStatement.setString(1, email);
	        	    preparedStatement.setString(2, password);
	        	    ResultSet rs = preparedStatement.executeQuery();
	        	  
	        	    if (rs.next()) {
	        	            // Passwords match, authentication successful
	        	    
	        	            User user = new User();
	        	            user.setUserId(rs.getInt("u_id"));
	        	            user.setFirstName(rs.getString("first_name"));
	        	            user.setLastName(rs.getString("last_name"));
	        	            user.setMobileNumber(rs.getString("mobilenumber"));
	        	            user.setEmail(rs.getString("email"));
	        	            user.setPassword(rs.getString("password"));
	        	            user.setRole(rs.getString("u_roles"));

	        	            HttpSession session = request.getSession();
	        	            session.setAttribute("userObj", user);
	        	            //password matches it logs in  
	        	            response.sendRedirect("index.jsp");
	        	            rs.close();
	        	            preparedStatement.close();
	        	            connection.close();
	        	            
	        	        } else {
	        	            // Passwords don't match, authentication failed
	        	            response.sendRedirect("LoginRegister.jsp?error=1");
	        	        }
	        	    
	        	} catch (Exception e) {
	        	    e.printStackTrace();
	        	    response.sendRedirect("LoginRegister.jsp?error=Database error");
	        	} 

				              
		    			//ResultSet rs = preparedStatement.executeQuery();
			    			

//			    			if (rs.next()) {
//												
//			    				
//			    				 // Create a new User object to hold user data
//			    		        User user = new User();			    		        
//			    		        // Retrieve data from the ResultSet and set it in the User object
//			    			    
//			    			    user.setUserId(rs.getInt("u_id"));
//			    		        user.setFirstName(rs.getString("first_name"));
//			    		        user.setLastName(rs.getString("last_name"));
//			    		        user.setMobileNumber(rs.getString("mobilenumber"));
//			    		        user.setEmail(rs.getString("email"));
//			    		        user.setPassword(rs.getString("password"));
//			    		        user.setRole(rs.getString("u_roles"));
//			     
//			   				 	HttpSession session = request.getSession();
//			   				 
//			   				 	session.setAttribute("userObj", user);
//			   				 
//							    response.sendRedirect("index.jsp");
//							    
//							} else {
//								response.sendRedirect("LoginRegister.jsp?error=1");
//							}
//						
//			    			System.out.println(preparedStatement);
//			    			
			    		
			            
	        } else if (action.equals("register")) {
	        	
	        	
	            // Handle registration logic
	        	

				String fname= request.getParameter("fname");
				String lname= request.getParameter("lname");
				String mobile= request.getParameter("mobile");
				String email= request.getParameter("email");
				String password= request.getParameter("password");
				String roles= request.getParameter("roles");


	            // Check if email already exists
	            if (userExists(email)) {
	            	
					response.sendRedirect("LoginRegister.jsp?emailAlready=1");
	                
	            }
	            else if (mobileNumberExists(mobile)) {
	            	response.sendRedirect("LoginRegister.jsp?mobileAlready=1");
	            }
	            else {
	                // Add new user to database or perform registration action
	            	
	                registerUser(fname,lname,mobile,email,password,roles);
	                
	                response.sendRedirect("LoginRegister.jsp");
	            }
	        }
	
			}
	
	
	
			private boolean userExists(String email) {
				
			    String CHECK_USER_QUERY = "SELECT * FROM tbl_users WHERE email = ?";
			    try {
			    	
			        Connection connection = ConnectionManager.getConnection();
			        
			        PreparedStatement checkUserStmt = connection.prepareStatement(CHECK_USER_QUERY);
			        checkUserStmt.setString(1, email);
			        
			        ResultSet rs = checkUserStmt.executeQuery();
			        
			        boolean userExists = rs.next();
			        
			        // Close resources
			        rs.close();
			        checkUserStmt.close();
			        connection.close();
			        
			        
			        return userExists;
			        
			    } catch (SQLException e) {
			        e.printStackTrace();
			        return false;
			    }
			}
			
			private boolean mobileNumberExists(String mobile) {
			    String CHECK_USER_MOBILE = "SELECT * FROM tbl_users WHERE mobileNumber = ?";
			    
			    try {
			        Connection connection = ConnectionManager.getConnection();
			        PreparedStatement checkMobileStmt = connection.prepareStatement(CHECK_USER_MOBILE);
			        checkMobileStmt.setString(1, mobile);
			        
			        ResultSet rs = checkMobileStmt.executeQuery();
			        
			        boolean mobileExists = rs.next();
			        
			        // Close resources
			        rs.close();
			        checkMobileStmt.close();
			        connection.close();
			        
			        return mobileExists;
			        
			    } catch (SQLException e) {
			        e.printStackTrace();
			        return false;
			    }
			}



			
			 private void registerUser(String fname, String lname, String mobile, String email, String password, String roles) {
			    // Implement your logic to register a new user
			    // For example, add user to a database
			
				
					try {
						
						User newUser = new User(fname,lname,mobile,email,password,roles);
						
						    userDao.insertUser(newUser);
			
						
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
			}
			
			}


