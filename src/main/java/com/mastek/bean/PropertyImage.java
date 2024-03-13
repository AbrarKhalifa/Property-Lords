package com.mastek.bean;

import java.util.*;

public class PropertyImage {

    private int imageId;
    private int property_id_fk;
    private List<String> images; // multiple property images


    public PropertyImage() {
    	this.images = new ArrayList<>();
    	
    };
    
    public PropertyImage(Property property) {
        this.property_id_fk = property.getPropertyId();
        this.images = new ArrayList<>();
        
    }
    
    public PropertyImage(int imageId,Property property) {
        this.imageId = imageId;
        this.property_id_fk = property.getPropertyId();
        this.images = new ArrayList<>();
    }
    
    

    public int getProperty_id_fk() {
		return property_id_fk;
	}

	public void setProperty_id_fk(Property property) {
		this.property_id_fk = property.getPropertyId();
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	
	 // Getter
    public List<String> getImages() {
        return images;
    }

    // Setter
    public void setImages(List<String> images) {
        this.images = images;
    }
    
    // Add single image to the list
    public void addSingleImage(String image) {
        this.images.add(image);
    }

    // Remove single image from the list
    public void removeSingleImage(String image) {
        this.images.remove(image);
    }

	
	
	@Override
    public String toString() {
        return "PropertyImage{" +
                "imageId=" + imageId +
                ", PropertyId=" + property_id_fk +
                ", image='" + images + '\'' +
                '}';
    }
}

