package com.mastek.bean;

public class PropertyAddress {

    private int addressId;
    private int property_id_fk;;
    private String landmark;
    private String society;
    private String city;
    private String state;
    private String pincode; // Added "pincode" field

    // Getters and setters (omitted for brevity)

    public PropertyAddress() {};
    
    public PropertyAddress(Property property,String landmark,String society, String city, String state, String pincode) {
        this.property_id_fk = property.getPropertyId();
        this.landmark = landmark;
        this.society = society;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }
    
    public PropertyAddress(int addressId,Property property,String landmark, String society, String city, String state, String pincode) {
        this.property_id_fk = property.getPropertyId();
    	this.addressId = addressId;
        this.landmark = landmark;
        this.society = society;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }
    
    

    public int getProperty_id_fk() {
		return property_id_fk;
	}

	public void setProperty_id_fk(Property property) {
		this.property_id_fk = property.getPropertyId();
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}


	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getSociety() {
		return society;
	}

	public void setSociety(String society) {
		this.society = society;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	@Override
    public String toString() {
        return "PropertyAddress{" +
                "addressId=" + addressId +
                ", propertyId=" + property_id_fk +
                ", landmark='" + landmark + '\'' +
                ", society='" + society + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pincode='" + pincode + '\'' +
                '}';
    }
}
