package com.mastek.bean;

public class Property {


	    private int propertyId;
	    //private User owner; // Using User class instead of int for 'u_id_fk'
	    private int agent_id_fk;
	    private String propertyType;
	    private String proSize;
	    private double price;
	    private String features;
	    private int noOfRooms;
	    private int noOfKitchens;
	    private int noOfBathrooms;
	    private String amenities;
	    private String status;
	    private String purpose;
//	    private List<PropertyImage> images; // multiple property images
//	    private PropertyAddress addresses; // List of property addresses
//	    private PropertyDocument documents; // List of property documents
	    
	    
	    private String Address;  // for display whole data to screen
	    private String imageData;  // additional 
	    


	    // Getters and setters (omitted for brevity)
	  


		public Property() {
	    };
	    
	   
//	    public void addImage(PropertyImage image) {
//
//	    	this.images.add(image);
//
//	    }
	    
	    
	    public Property(User owner,String propertyType, String proSize, double price, String features, int noOfRooms,
				int noOfKitchens, int noOfBathrooms, String amenities, String status, String purpose) {  //PropertyAddress addresses, PropertyDocument documents
			super();
			this.agent_id_fk = owner.getUserId();
			this.propertyType = propertyType;
			this.proSize = proSize;
			this.price = price;
			this.features = features;
			this.noOfRooms = noOfRooms;
			this.noOfKitchens = noOfKitchens;
			this.noOfBathrooms = noOfBathrooms;
			this.amenities = amenities;
			this.status = status;
			this.purpose = purpose;
//			this.images = new ArrayList<>();
//			this.addresses = addresses;
//			this.documents = documents;
		}
	    

	    public Property(int propertyId,User owner, String propertyType, String proSize, double price, String features,
	                     int noOfRooms, int noOfKitchens, int noOfBathrooms, String amenities, String status, String purpose) { //  PropertyAddress addresses, PropertyDocument documents
	        this.propertyId = propertyId;
			this.agent_id_fk = owner.getUserId();
	        this.propertyType = propertyType;
	        this.proSize = proSize;
	        this.price = price;
	        this.features = features;
	        this.noOfRooms = noOfRooms;
	        this.noOfKitchens = noOfKitchens;
	        this.noOfBathrooms = noOfBathrooms;
	        this.amenities = amenities;
	        this.status = status;
	        this.purpose = purpose;
//	        this.images = new ArrayList<>();
//	        this.addresses = addresses;
//	        this.documents = documents;
	    }

	   

		// Methods for adding and managing images, addresses, and documents (omitted for brevity)

	    public int getAgent_id_fk() {
			return agent_id_fk;
		}


		public void setAgent_id_fk(User owner) {
			this.agent_id_fk = owner.getUserId();
		}


		public int getPropertyId() {
			return propertyId;
		}

		public void setPropertyId(int propertyId) {
			this.propertyId = propertyId;
		}


		public String getPropertyType() {
			return propertyType;
		}

		public void setPropertyType(String propertyType) {
			this.propertyType = propertyType;
		}

		public String getProSize() {
			return proSize;
		}

		public void setProSize(String proSize) {
			this.proSize = proSize;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public String getFeatures() {
			return features;
		}

		public void setFeatures(String features) {
			this.features = features;
		}

		public int getNoOfRooms() {
			return noOfRooms;
		}

		public void setNoOfRooms(int noOfRooms) {
			this.noOfRooms = noOfRooms;
		}

		public int getNoOfKitchens() {
			return noOfKitchens;
		}

		public void setNoOfKitchens(int noOfKitchens) {
			this.noOfKitchens = noOfKitchens;
		}

		public int getNoOfBathrooms() {
			return noOfBathrooms;
		}

		public void setNoOfBathrooms(int noOfBathrooms) {
			this.noOfBathrooms = noOfBathrooms;
		}

		public String getAmenities() {
			return amenities;
		}

		public void setAmenities(String amenities) {
			this.amenities = amenities;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getPurpose() {
			return purpose;
		}

		public void setPurpose(String purpose) {
			this.purpose = purpose;
		}
		
		


	    public String getAddress() {
			return Address;
		}

		public void setAddress(String address) {
			Address = address;
		}

		  
	    public String getImageData() {
			return imageData;
		}


		public void setImageData(String imageData) {
			this.imageData = imageData;
		}
		
//
//		public List<PropertyImage> getImages() {
//			return images;
//		}
//
//		public void setImages(List<PropertyImage> images) {
//			this.images = images;
//		}
//
//		public PropertyAddress getAddresses() {
//			return addresses;
//		}
//
//		public void setAddresses(PropertyAddress addresses) {
//			this.addresses = addresses;
//		}
//
//		public PropertyDocument getDocuments() {
//			return documents;
//		}
//
//		public void setDocuments(PropertyDocument documents) {
//			this.documents = documents;
//		}
		
	//	{	    	this.agent_id_fk = owner.getUserId();}

		@Override
	    public String toString() {
	        return "Property{" +
	                "propertyId=" + propertyId +
	                ", ownerId="+ agent_id_fk+
	               // ", owner=" + owner +
	                ", propertyType='" + propertyType + '\'' +
	                ", proSize='" + proSize + '\'' +
	                ", price=" + price +
	                ", features='" + features + '\'' +
	                ", noOfRooms=" + noOfRooms +
	                ", noOfKitchens=" + noOfKitchens +
	                ", noOfBathrooms=" + noOfBathrooms +
	                ", amenities='" + amenities + '\'' +
	                ", status='" + status + '\'' +
	                ", purpose='" + purpose + '\'' +
	                '}';
	    }
	}

	