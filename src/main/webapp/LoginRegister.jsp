<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
        $(document).ready(function(){
            $("#registerLink").click(function(){
                $("#loginForm").hide();
                $("#registerForm").show();
            });

            $("#loginLink").click(function(){
                $("#registerForm").hide();
                $("#loginForm").show();
            });
        });
    </script>
    
    <!-- Your custom CSS overrides (if any) -->
    <style>
      /* Add your custom CSS overrides here */

      .divider:after, .divider:before {
          content: "";
          flex: 1;
          height: 1px;
          background: #eee;
      }
      .h-custom {
          height: calc(100% - 73px);
      }
      @media (max-width: 450px) {
          .h-custom {
              height: 100%;
          }
      }
      
      /* Customize the button color */
      .btn-primary {
          background-color: rgb(35, 66, 35);
          border-color: rgb(35, 66, 35);
      }
      
      /* Customize the copyrights background */
      .bg-primary {
          background-color:rgb(35, 66, 35);
      }
  </style>
</head>
<body>

 
 			<jsp:include page="navbar.jsp" />
		   <%-- Display error message if login fails --%>
			    <%
			    String error = request.getParameter("error");
			    String emailAlready = request.getParameter("emailAlready");

			    if (error != null && error.equals("1")) {
			    %>
			        <div class="alert alert-danger" role="alert">
			            Invalid Email or Password. Please try again.
			        </div>
			    <%
			    }
			    if(emailAlready != null && emailAlready.equals("1")){
			    %>
			    <div class="alert alert-danger" role="alert">
			        Email Already Register. Please Login...
			    </div>
			    <%
			    }
			    if(emailAlready != null && emailAlready.equals("User not found"))
			    {
			    %>
			     <div class="alert alert-danger" role="alert">
			        User not found ...
			    </div>
			    <%
			    }
			    if(emailAlready != null && emailAlready.equals("Database error"))
			    {
			    %>
			     <div class="alert alert-danger" role="alert">
			        Database error ...
			    </div>
			    <%
			    }
			    %>
			    
			    
			    
			    
    <div id="loginForm">

    <section class="mt-5">
        <div class="container-fluid h-custom">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-md-9 col-lg-6 col-xl-5">
                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp" class="img-fluid" alt="Sample image">
                </div>
                <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                
                        <div class="d-flex flex-row align-items-center justify-content-center justify-content-lg-start">
                           
                           
                        </div>
                        <div class="divider d-flex align-items-center my-4">
                        <p class="text-center fw-bold mx-3 mb-0">Login Form</p>
                          
                        </div>
                        
                   <form id="login" action="LoginRegisterServlet" method="POST">
            	   <input type="hidden" name="action" value="login">
                        <!-- Email input -->
                        <div class="form-outline mb-4">
                            <label class="form-label" for="form3Example3">Email address</label>
                    
                            <input type="email" id="form3Example3" name = "email" class="form-control form-control-lg" placeholder="Enter a valid email address" />
                        </div>
                        <!-- Password input -->
                        <div class="form-outline mb-3">
                             <label class="form-label" for="form3Example4">Password</label>
                        
                            <input type="password" id="form3Example4"  name ="password" class="form-control form-control-lg" placeholder="Enter password" />
                        </div>
                        <div class="d-flex justify-content-between align-items-center">
                            <!-- Checkbox -->
                            <div class="form-check mb-0">
                                <input class="form-check-input me-2" type="checkbox" value="" id="form2Example3" />
                                <label class="form-check-label" for="form2Example3">
                                    Remember me
                                </label>
                            </div>
                            <a href="" class="text-body">Forgot password?</a> <!-- Redirects to the registration page -->
                        </div>
                        <div class="text-center text-lg-start mt-4 pt-2">                        
                            <input type="submit" class="btn btn-primary btn-lg" value = "Login" style="padding-left: 2.5rem; padding-right: 2.5rem;">
                            <p class="small fw-bold mt-2 pt-1 mb-0">Don't have an account? <a href="#" id="registerLink" class="link-danger">Register</a></p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
    </section>
    
    </div>
    
    
   <div id="registerForm" style="display:none;">
   
    <section class="vh-100">
        <div class="container-fluid h-custom">
        
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-md-9 col-lg-6 col-xl-5">
                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp" class="img-fluid" alt="Sample image">
                </div>
                <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                        <div class="divider d-flex align-items-center my-4">
                            <p class="text-center fw-bold mx-3 mb-0">Registration Form</p>
                        </div>
                        
                     <form id="register" action="LoginRegisterServlet" method="POST">
            		<input type="hidden" name="action" value="register">   
                        <!-- First Name input -->
                        <div class="form-outline mb-4">
                            <label for="firstName">First Name:</label>
                            <input type="text" id="firstName" name="fname" class="form-control form-control-lg" placeholder="Enter your first name" required>
                        </div>
                        <!-- Last Name input -->
                        <div class="form-outline mb-4">
                            <label for="lastName">Last Name:</label>
                            <input type="text" id="lastName" name="lname" class="form-control form-control-lg" placeholder="Enter your last name" required>
                        </div>
                        <!-- Mobile Number input -->
                        <div class="form-outline mb-4">
                            <label for="mobileNumber">Mobile Number:</label>
                            <input type="text" id="mobileNumber" name="mobile" class="form-control form-control-lg" placeholder="Enter your mobile number (10 digits)" required>
                        </div>
                        <!-- Email input -->
                        <div class="form-outline mb-4">
                            <label for="email">Email address:</label>
                            <input type="email" id="email" name="email" class="form-control form-control-lg" placeholder="Enter a valid email address" required>
                        </div>
                        <!-- Password input -->
                        <div class="form-outline mb-4">
                            <label for="password">Password:</label>
                            <input type="password" id="password" name="password" class="form-control form-control-lg" placeholder="Enter password (at least 8 characters with alphanumeric and special characters)" required>
                        </div>
                        <!-- User Roles input -->
                        <div class="form-outline mb-4">
                            <label for="userRoles">Register as</label>
                            <select id="userRoles" name="roles" class="form-control form-control-lg" required>
                                 <option value="" selected disabled>Select role</option>
                         		<option value="admin">Admin</option>
                                <option value="user">User</option>
                                <option value="agent">Agent</option>
                                <option value="landlord">Landlord</option>
                                <option value="tenant">Tenant</option>
                            </select>
                        </div>
                        <div class="d-flex justify-content-between align-items-center">
                            <!-- Checkbox -->
                            <div class="form-check mb-0">
                                <input class="form-check-input me-2" type="checkbox" value="" id="agreeTerms" required>
                                <label class="form-check-label" for="agreeTerms">
                                    I agree to the terms and conditions
                                </label>
                            </div>
                        </div>
                        <div class="text-center text-lg-start mt-4 pt-2">
                            <input type="submit" class="btn btn-primary btn-lg" value = "Register" style="padding-left: 2.5rem; padding-right: 2.5rem;">
                            <p class="small fw-bold mt-2 pt-1 mb-0">Already have an account? <a href="#" id="loginLink" class="link-danger">Login</a></p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
    </section>
    </div>
    
    
    
     			

    <!-- Bootstrap Bundle with Popper (JavaScript) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
