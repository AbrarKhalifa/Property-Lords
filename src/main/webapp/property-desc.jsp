<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Property Details</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
   	<link rel="stylesheet" href="css/propertyDetail.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    
</head>

<body>

		<jsp:include page="navbar.jsp" />

        <div class="row no-gutters">
            <div class="col-md-6 mt-5">
                <div class="property-details slideshow-container">
    				<div class="rent-box">For ${propertyDesc.purpose}</div> 	 
    				
    				 <c:forEach items="${propertyDesc.images}" var="image">
			            <img src="${image.url}" class="mySlides" alt="Property Image">
			        </c:forEach>
                  
                    <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
                    <a class="next" onclick="plusSlides(1)">&#10095;</a>
                </div>
            </div>
            <div class="col-md-6 mt-5">
                <div class="property-info property-details" >
                    <h2 class="text-white">Property Information</h2>
                    <a href="property-list.jsp">
                        <div class="back-arrow" >
                        
                          
							<i class="fa fa-arrow-left" style="color:white" aria-hidden="true"></i>
                            <div class="tooltip">Back To Home</div>
                        </div>
                    </a>
                    
                    <div class="table-responsive" >
                        <table class="table text-white">
                            <tbody>
                            <tr>
                            
                             
                               <td><strong>Property type:</strong></td>
                                    <td><Strong> ${propertyDesc.propertyType}</Strong></td>
                                </tr>
                                <tr>
                                    <td><strong>Features:</strong></td>
                                    <td><strong>${propertyDesc.features}</strong></td>
                                </tr>
                                
                                <tr>
                                    <td><strong>Size:</strong></td>
                                    <td><strong>${propertyDesc.proSize} sqft</strong></td>
                                </tr>
                                <tr>
                                    <td><strong>Rooms:</strong></td>
                                    <td><Strong>${propertyDesc.noOfRooms}</Strong></td>
                                </tr>
                                <tr>
                                    <td><strong>Kitchens:</strong></td>
                                    <td><strong>${propertyDesc.noOfKitchens}</strong></td>
                                </tr>
                                <tr>
                                    <td><strong>Bathrooms:</strong></td>
                                    <td><strong>${propertyDesc.noOfBathrooms}</strong></td>
                                </tr>
                                <tr>
                                    <td><strong>price:</strong></td>
                                    <td><strong>${propertyDesc.price}</strong></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

    
    <div class="location-info">
        <h2 class="text-white">Amenities</h2>
        
        <div class="amenity-box ">
              <c:forEach var="amenity" items="${amenities}">
                 <div class="amenity"><strong><c:out value="${amenity}"/></strong></div>
              
     	  	  </c:forEach>
     	<!-- 
            <div class="amenity"><strong>Garden</strong></div>
            <div class="amenity"><strong>Pool</strong></div>
            <div class="amenity"><strong>Gym</strong></div>
            <div class="amenity"><strong>Lift</strong></div>
            <div class="amenity"><strong>Swimming Pool</strong></div> -->
        </div>
    </div>
    <div class="location-info">
        <h2 class="text-white">Location Information</h2>
        <div class="amenity-box">
            <div class="amenity"><strong>Society: ${propertyDesc.addresses.society}</strong></div>
            <div class="amenity"><strong>City: ${propertyDesc.addresses.city}</strong></div>
            <div class="amenity"><strong>State:  ${propertyDesc.addresses.state}</strong></div>
            <div class="amenity"><strong>Pin Code: ${propertyDesc.addresses.pincode}</strong></div>
        </div>
    </div>
    

    <div class="mt-4">
        <button type="button" class="btn btn-primary " style=" border-radius: 20px;">Book an Appointment</button>
        <button type="button" class="btn btn-primary" style=" border-radius: 20px;">Submit Rent Documents</button>
        
    </div>
    
    
    
    
    <script>
        function goBack() {
            window.history.back();
        }
    </script>

    <script>
        var slideIndex = 1;
        showSlides(slideIndex);

        function plusSlides(n) {
            showSlides(slideIndex += n);
        }

        function currentSlide(n) {
            showSlides(slideIndex = n);
        }

        function showSlides(n) {
            var i;
            var slides = document.getElementsByClassName("mySlides");
            if (n > slides.length) { slideIndex = 1 }
            if (n < 1) { slideIndex = slides.length }
            for (i = 0; i < slides.length; i++) {
                slides[i].style.display = "none";
            }
            slides[slideIndex - 1].style.display = "block";
        }
    </script>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>