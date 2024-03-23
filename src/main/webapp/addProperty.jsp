<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.mastek.bean.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Property</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Your custom CSS overrides (if any) -->
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <%
    // Check if the user is logged in he should not be able to see add property form/page
    User user = (User) session.getAttribute("userObj");
    if (user == null || user.getRole().equals("user")) {
           	response.sendRedirect("404.jsp");
        return; // Stop further execution of the page
    }
%>
    
    <!-- Your custom CSS overrides (if any) -->
    <style>
        /* Add your custom CSS overrides here */
        .form-group label,.divider
        {
    	color: white;
     }
        .divider:after, .divider:before {
            content: "";
            flex: 1;
            height: 1px;
            background: #61aced;
        }
        #2e53db

        .h-custom {
            height: calc(100% - 73px);
        }
        .amenities-checkboxes {
		    display: flex;
		    flex-wrap: wrap;
		}
		
		.amenities-checkboxes div {
		    flex-basis: 50%; /* Two columns */
		}
        @media (max-width: 450px) {
            .h-custom {
                height: 100%;
            }
        }
        /* Custom style for smaller form fields */
        .form-group {
            margin-bottom: 1.5rem;
            
        }
        label {
            font-weight: bold;
        }
        .form-container {
            background: linear-gradient(135deg, #153fead7, #67d6bc); 
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1);
        }
        .register-button {
            background-color: #224cbe;
            border: none;
        }
        .register-button:hover {
            background-color: #27d856;
        }
     
      
    </style>
</head>
<body>


 			<jsp:include page="navbar.jsp" />
 <section class="vh-100 mt-5">
    <div class="container-fluid h-custom">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-10">
                    <div class="form-container">
                        <div class="row">
                            <!-- First Column - Property Details -->
                            <div class="col-md-6">
                                <form id="propertyDetailsForm">
                                    <div class="divider d-flex align-items-center my-4">
                                        Property Details
                                    </div>
                                    <!-- Property Type dropdown -->
                                    <div class="form-group">
                                        <label for="propertyType">Property Type</label>
                                        <select id="propertyType" name="propertyType" class="form-select" required>
                                            <option value="">Select property type</option>
                                            <option value="House">House</option>
                                            <option value="Flat">Flat</option>
                                            <option value="Studio">Studio</option>
                                            <option value="Apartment">Apartment</option>
                                        </select>
                                        <div id="propertyTypeFeedback" class="invalid-feedback"></div>
                                    </div>
                                    <!-- Property Size input -->
                                    <div class="form-group">
                                        <label for="propertySize">Property Size</label>
                                        <input type="text" id="propertySize" name="propertySize" class="form-control" placeholder="Enter property size" required />
                                        <div id="propertySizeFeedback" class="invalid-feedback"></div>
                                    </div>
                                    <!-- Price input -->
                                    <div class="form-group">
                                        <label for="price">Price</label>
                                        <input type="number" id="price" name="price" class="form-control" placeholder="Enter price" min="1000" required />
                                        <div id="priceFeedback" class="invalid-feedback"></div>
                                    </div>
                                    <!-- Features input -->
                                    
                                    <div class="form-group">
									    <label for="features">Features</label>
									    <select id="features" name="features" class="form-select" placeholder="select feature" required>
									        <option value="">Select property features</option>									    
									        <option value="Semi-furnished">Semi-furnished</option>
									        <option value="Fully furnished">Fully furnished</option>
									        <option value="Non-furnished">Non-furnished</option>
									    </select>
									    <div id="featuresFeedback" class="invalid-feedback"></div>
									</div>
                                    <!-- Number of Rooms input -->
                                    <div class="form-group">
                                        <label for="numRooms">Number of Rooms</label>
                                        <input type="number" id="numRooms" name="numRooms" class="form-control" value="1" min="1" required />
                                    </div>
                                    <!-- Number of Kitchens input -->
                                    <div class="form-group">
                                        <label for="numKitchens">Number of Kitchens</label>
                                        <input type="number" id="numKitchens" name="numKitchens" class="form-control" value="1" min="1" required />
                                    </div>
                                    <!-- Number of Bathrooms input -->
                                    <div class="form-group">
                                        <label for="numBathrooms">Number of Bathrooms</label>
                                        <input type="number" id="numBathrooms" name="numBathrooms" class="form-control" value="1" min="1" required />
                                    </div>
                                    <!-- Property Images upload input -->
									
									<div class="form-group">
									    <label for="propertyImages">Upload Property Images</label> 
									    <input type="file" id="propertyImages" name="propertyImages" class="form-control image-upload" multiple required />
									    <div id="propertyImagesError" class="text-danger"></div>
									</div>
									<div class="form-group">
									    <label for="propertyDocImages">Upload Property Document Images</label>
									    <input type="file" id="propertyDocImages" name="propertyDocImages" class="form-control image-upload" />
									    <div id="propertyDocImagesError" class="text-danger"></div>
									</div>


                                </form>
                            </div>

                            <!-- Second Column - Property Address Details -->
                            <div class="col-md-6">
                                <form id="addressDetailsForm">
                                    <div class="divider d-flex align-items-center my-4">
                                        Property Address Details
                                    </div>
                                    <!-- Landmark input -->
                                    <div class="form-group">
                                        <label for="landmark">Landmark</label>
                                        <input type="text" id="landmark" name="landmark" class="form-control" placeholder="Enter landmark" required />
                                    </div>
                                    <!-- Society input -->
                                    <div class="form-group">
                                        <label for="society">Society</label>
                                        <input type="text" id="society" name="society" class="form-control" placeholder="Enter society" required />
                                    </div>
                                    <!-- City input -->
                                    <div class="form-group">
                                        <label for="city">City</label>
                                        <input type="text" id="city" name="city" class="form-control" placeholder="Enter city" required />
                                    </div>
                                    <!-- State input -->
                                    <div class="form-group">
                                        <label for="state">State</label>
                                        <input type="text" id="state" name="state" class="form-control" placeholder="Enter state" required />
                                    </div>
                                    <!-- Pincode input -->
                                    <div class="form-group">
                                        <label for="pincode">Pincode</label>
                                        <input type="text" id="pincode" name="pincode" class="form-control" placeholder="Enter pincode" pattern="[1-9][0-9]{5}" required />
                                        <div id="pincodeFeedback" class="invalid-feedback">Please enter a valid pincode.</div>
                                    </div>
                                   
                                    <!-- Amenities input -->
                                    <div class="form-group">
                                        <label for="amenities">Amenities</label>
                                        <input type="text" id="amenities" name="amenities" class="form-control" placeholder="Enter property amenities" required />
                                    </div>
                                    
                                    <!-- Status input -->
                                    <div class="form-group">
                                        <label for="status">Status</label>
                                        <select id="status" name="status" class="form-select" required>
                                            <option value="">Select status</option>
                                            <option value="Available">Available</option>
                                            <option value="Not Available">Not Available</option>
                                        </select>
                                        <div id="statusFeedback" class="invalid-feedback">Please select a status.</div>
                                    </div>
                                    <!-- Purpose input -->
                                    <div class="form-group">
                                        <label for="purpose">Purpose</label>
                                        <select id="purpose" name="purpose" class="form-select" required>
                                            <option value="">Select purpose</option>
                                            <option value="Rent">Rent</option>
                                            <option value="Sell">Sell</option>
                                        </select>
                                        <div id="purposeFeedback" class="invalid-feedback">Please select a purpose.</div>
                                    </div>
                                    <!-- Register Property Button -->
                                    <div class="form-group">
                                        <button class="btn btn-primary btn-lg btn-block register-button">Register Property</button>
                                    </div>
                                    <!-- Property Document Images upload input -->
                                </form>
                            </div>
                        </div>
                        </div>
                </div>
            </div>
        </div>
        </div>
    </section>
    <!-- Bootstrap Bundle with Popper (JavaScript) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Your custom JavaScript -->
    <!-- Your custom JavaScript -->
<script>
    // Function to validate the property registration form
    function validateForm() {
        // Get references to form inputs
        var propertyType = document.getElementById('propertyType');
        var propertySize = document.getElementById('propertySize');
        var price = document.getElementById('price');
        var features = document.getElementById('features');
        var numRooms = document.getElementById('numRooms');
        var numKitchens = document.getElementById('numKitchens');
        var numBathrooms = document.getElementById('numBathrooms');
        var propertyImages = document.getElementById('propertyImages');
        var landmark = document.getElementById('landmark');
        var society = document.getElementById('society');
        var city = document.getElementById('city');
        var state = document.getElementById('state');
        var pincode = document.getElementById('pincode');
        var amenities = document.getElementById('amenities');
        var status = document.getElementById('status');
        var purpose = document.getElementById('purpose');
        var propertyDocImages = document.getElementById('propertyDocImages');

        // Check if all required fields are filled
        if (!propertyType.value || !propertySize.value || !price.value || !features.value ||
            !numRooms.value || !numKitchens.value || !numBathrooms.value || !propertyImages.value ||
            !landmark.value || !society.value || !city.value || !state.value || !pincode.value ||
            !amenities.value || !status.value || !purpose.value || !propertyDocImages.value) {
            alert('Please fill in all required fields.');
            return false; // Prevent form submission
        }

        // Validate Property Size
        if (isNaN(propertySize.value) || propertySize.value <= 0) {
        alert('Property size must be a valid number greater than 0.');
        return false;
    }

        // Validate Price
        if (price.value <= 0) {
            alert('Price must be greater than 0.');
            return false;
        }

        // Validate Pincode (assuming 6 digits for Indian pincode)
        var pincodeRegex = /^[1-9][0-9]{5}$/;
        if (!pincodeRegex.test(pincode.value)) {
            alert('Please enter a valid 6-digit pincode.');
            return false;
        }

        // Validate State (you can add more specific validation logic for Indian states if needed)

        // If all validations pass, return true to allow form submission
        return true;
    }

    // Add event listener to the form submit button
    document.querySelector('.register-button').addEventListener('click', function (event) {
        // Prevent default form submission behavior
        event.preventDefault();

        // Call the validateForm function before submitting the form
        if (validateForm()) {
            // If validation passes, submit the form
            document.getElementById('propertyDetailsForm').submit();
        }
    });
    // function to validate image Extensions
    function validateImageFileType(input, errorElementId) {
        var files = input.files;
        var errorElement = document.getElementById(errorElementId);
        var allowedExtensions = ["jpg", "jpeg", "png"];
        var invalidFiles = [];

        for (var i = 0; i < files.length; i++) {
            var fileName = files[i].name;
            var fileExtension = fileName.split('.').pop().toLowerCase();
            if (!allowedExtensions.includes(fileExtension)) {
                invalidFiles.push(fileName);
            }
        }

        if (invalidFiles.length > 0) {
            var errorMessage = " Only JPG, JPEG, and PNG files are allowed..";
            errorElement.textContent = errorMessage;
            input.setCustomValidity(" Only JPG, JPEG, and PNG files are allowed..");
            return false;
        } else {
            errorElement.textContent = ""; 
            return true;
        }
    }


    // Event listener for image file input change
    document.getElementById('propertyImages').addEventListener('change', function() {
        validateImageFileType(this, 'propertyImagesError');
    });

    document.getElementById('propertyDocImages').addEventListener('change', function() {
        validateImageFileType(this, 'propertyDocImagesError');
    });


</script>


</body>
</html>