package com.mastek.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
import com.mastek.dao.UserDao;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * Servlet implementation class LoginRegisterServlet
 */
@WebServlet("/LoginRegisterServlet")
public class LoginRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDao userDao;

	 
    private String jdbcURL ="jdbc:oracle:thin:@localhost:1521:xe";
	private String jdbcUsername ="finalProject";
	private String jdbcPassword ="sys";
	private String jdbcDriver ="oracle.jdbc.driver.OracleDriver";
	
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
	        	Connection connection = null;
	        	PreparedStatement preparedStatement = null;
	        	ResultSet rs = null;

	        	String query = "SELECT * FROM tbl_users WHERE email = ?";

	        	try {
	        	    Class.forName(jdbcDriver);
	        	    connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
	        	    preparedStatement = connection.prepareStatement(query);
	        	    preparedStatement.setString(1, email);
	        	    rs = preparedStatement.executeQuery();

	        	    if (rs.next()) {
	        	        String hashedPasswordFromDB = rs.getString("password");
	        	        boolean passwordMatches = BCrypt.verifyer().verify(password.toCharArray(), hashedPasswordFromDB).verified;

	        	        if (passwordMatches) {
	        	            // Passwords match, authentication successful
	        	            User user = new User();
	        	            user.setUserId(rs.getInt("u_id"));
	        	            user.setFirstName(rs.getString("first_name"));
	        	            user.setLastName(rs.getString("last_name"));
	        	            user.setMobileNumber(rs.getString("mobilenumber"));
	        	            user.setEmail(rs.getString("email"));
	        	            user.setRole(rs.getString("u_roles"));

	        	            HttpSession session = request.getSession();
	        	            session.setAttribute("userObj", user);
	        	            response.sendRedirect("index.jsp");
	        	        } else {
	        	            // Passwords don't match, authentication failed
	        	            response.sendRedirect("LoginRegister.jsp?error=1");
	        	        }
	        	    } else {
	        	        // User not found
	        	        response.sendRedirect("LoginRegister.jsp?error=User not found");
	        	    }
	        	} catch (Exception e) {
	        	    e.printStackTrace();
	        	    response.sendRedirect("LoginRegister.jsp?error=Database error");
	        	} finally {
	        	    // Close resources in finally block
	        	    try {
	        	        if (rs != null) rs.close();
	        	        if (preparedStatement != null) preparedStatement.close();
	        	        if (connection != null) connection.close();
	        	    } catch (SQLException ex) {
	        	        ex.printStackTrace();
	        	    }
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
	                
	            } else {
	                // Add new user to database or perform registration action
	                registerUser(fname,lname,mobile,email,password,roles);
	                response.sendRedirect("LoginRegister.jsp");
	            }
	        }
	
			}
	
	
	
			private boolean userExists(String email) {
				
			    String CHECK_USER_QUERY = "SELECT * FROM tbl_users WHERE email = ?";
			    
			    try {
			    	
			        Class.forName(jdbcDriver);
			        Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			        
			        PreparedStatement checkUserStmt = connection.prepareStatement(CHECK_USER_QUERY);
			        checkUserStmt.setString(1, email);
			        
			        ResultSet rs = checkUserStmt.executeQuery();
			        
			        boolean userExists = rs.next();
			        
			        // Close resources
			        rs.close();
			        checkUserStmt.close();
			        connection.close();
			        
			        
			        return userExists;
			        
			    } catch (ClassNotFoundException | SQLException e) {
			        e.printStackTrace();
			        return false;
			    }
			}

			
			 private void registerUser(String fname, String lname, String mobile, String email, String password, String roles) {
			    // Implement your logic to register a new user
			    // For example, add user to a database
			
				
					try {
						int costFactor = 12;
						String hashedPassword = BCrypt.withDefaults().hashToString(costFactor, password.toCharArray());
						System.out.println("pass "+hashedPassword);
						
						User newUser = new User(fname,lname,mobile,email,hashedPassword,roles);			
						    userDao.insertUser(newUser);
			
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			
			}


