package org.campusincident.webservice.geolocation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

//This class stores information about a location on Earth.  Locations are
//specified using latitude and longitude.  The class includes a method for
//computing the distance between two locations.

@Data
@Entity
public class Geolocation {

 private @Id @GeneratedValue Long id;
 private double latitude;
 private double longitude;
 
 public Geolocation() {}

 public Geolocation(double theLatitude, double theLongitude) {
     latitude = theLatitude;
     longitude = theLongitude;
 }

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
}
