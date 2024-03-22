<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ page import="com.mastek.bean.User" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>


    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Inter:wght@700;800&display=swap" rel="stylesheet">
    
    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="lib/animate/animate.min.css" rel="stylesheet">
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
	<link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="css/style.css" rel="stylesheet">

</head>
<body>



    <%
        User user = (User) session.getAttribute("userObj");
    %>

	  
        <!-- Navbar Start -->
        <div class="container-fluid nav-bar bg-transparent">
            <nav class="navbar navbar-expand-lg bg-white navbar-light py-0 px-4">
                <a href="index.html" class="navbar-brand d-flex align-items-center text-center">
                    <div class="icon p-2 me-2">
                        <img class="img-fluid" src="img/icon-deal.png" alt="Icon" style="width: 30px; height: 30px;">
                    </div>
                    <h1 class="m-0 text-primary">PropertyLords</h1>
                </a>
                <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarCollapse">
                    <div class="navbar-nav ms-auto">
                        <a href="index.jsp" class="nav-item nav-link active">Home</a>
                        <a href="about.jsp" class="nav-item nav-link">About</a>
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Property</a>
                            <div class="dropdown-menu rounded-0 m-0">
                                <a href="property-list.jsp" class="dropdown-item">Property List</a>
                                <a href="property-type.jsp" class="dropdown-item">Property Type</a>
                                <a href="property-agent.jsp" class="dropdown-item">Property Agent</a>
                            </div>
                        </div>
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Pages</a>
                            <div class="dropdown-menu rounded-0 m-0">
                                <a href="testimonial.jsp" class="dropdown-item">Testimonial</a>
                                <a href="404.jsp" class="dropdown-item">404 Error</a>
                            </div>
                        </div>
                        <a href="contact.jsp" class="nav-item nav-link">Contact</a>
                    </div>
                    
         	 <%  	
             if(user != null && user.getRole().equals("agent") || user != null && user.getRole().equals("landlord") || user != null && user.getRole().equals("admin"))
             {
            	              
             %> 
                    <a href="addProperty.jsp" class="btn btn-primary px-3 d-none d-lg-flex">Add Property</a>
              <%
             } 
             %>  
             
              
            <%
            	if(user != null){
            %>
            		<div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Hello, <%= user.getFirstName() %></a>
                            <div class="dropdown-menu rounded-0 m-0">
                                <a href="logoutServlet" class="px-3 d-none d-lg-flex"> Logout </a>
                                
                            </div>
                        </div>
                                
            <%
            	} 
            	else
            	{
            %>
            
            			 <a href="LoginRegister.jsp" class="btn btn-primary px-3 d-none d-lg-flex"> Login </a>
            		
                
             <%
            	}
             %> 
             
                      
                </div>
            </nav>
        </div>
        <!-- Navbar End -->


</body>
</html>