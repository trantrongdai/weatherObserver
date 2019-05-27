package com.thrift.Modes2;

public class Location {
	private byte locationID;
	private String name;
	// Latitude and Longitude using ISO 6709, where we require fractions
	private double latitude;
	private double longtitude;
	private String description = "";
	
	public Location(byte locationID, String name, double latitude, double longtitude) {
		super();
		this.locationID = locationID;
		this.name = name;
		this.latitude = latitude;
		this.longtitude = longtitude;
	}

	public Location(byte locationID, String name, double latitude, double longtitude, String description) {
		super();
		this.locationID = locationID;
		this.name = name;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.description = description;
	}
	
	// Get Methode
	public byte getLocationID() {
		return this.locationID;
	}
	public String getName() {
		return this.name;
	}
	public double getLatitude() {
		return this.latitude;
	}
	public double getLongtitude() {
		return this.longtitude;
	}
	public String getDescription() {
		return this.description;
	}
	
	// Set Methode
	public void setLocationID(byte locationID) {
		this.locationID = locationID;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	// display all detail
	public void display() {
		System.out.println(this.locationID + " " + this.name + " " + this.latitude + " " + this.longtitude + " " + this.description);
	}
}
