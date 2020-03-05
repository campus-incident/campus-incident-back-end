package org.campusincident.webservice.location;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.campusincident.webservice.geolocation.Geolocation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
public class Location {

	@Id @GeneratedValue
	private Long id;
	
	@ManyToOne
	private Geolocation center;
	
	@ApiModelProperty(notes = "In meters")
	@NotNull
	@DecimalMin(value = "0.0", message = "Radius cannot be negative")
	private double radius;
	
	@NotBlank(message = "Name cannot be null")
	private String name;
	
	@NotBlank(message = "TzName cannot be null or empty string")
	private String tzName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Geolocation getCenter() {
		return center;
	}

	public void setCenter(Geolocation center) {
		this.center = center;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTzName() {
		return tzName;
	}

	public void setTzName(String tzName) {
		this.tzName = tzName;
	}
	
}
