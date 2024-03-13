package com.mastek.bean;

public class PropertyDocument {

    private int documentId;
    private int property_id_fk;
    private String documentImage; // Assuming String for document data

    // Getters and setters (omitted for brevity)

    public PropertyDocument() {};
    
    public PropertyDocument(Property property,String documentImage) {
        this.property_id_fk = property.getPropertyId();
        this.documentImage = documentImage;
    }
    
    public PropertyDocument(int documentId,Property property,String documentImage) {
        this.property_id_fk = property.getPropertyId();
        this.documentId = documentId;
        this.documentImage = documentImage;
    }

    
    
    
    public int getProperty_id_fk() {
		return property_id_fk;
	}

	public void setProperty_id_fk(Property property) {
		this.property_id_fk = property.getPropertyId();
	}

	public int getDocumentId() {
		return documentId;
	}

	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}

	

	public String getDocumentImage() {
		return documentImage;
	}

	public void setDocumentImage(String documentImage) {
		this.documentImage = documentImage;
	}

	@Override
    public String toString() {
        return "PropertyDocument{" +
                "documentId=" + documentId +
                ", propertyId=" + property_id_fk +
                ", documentImage='" + documentImage + '\'' +
                '}';
    }
}
