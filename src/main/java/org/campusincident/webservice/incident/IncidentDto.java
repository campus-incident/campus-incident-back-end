package org.campusincident.webservice.incident;

import java.time.Instant;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class IncidentDto {
	
	@ApiModelProperty(notes = "email", required = true)
	@NotBlank(message = "Author cannot be null nor empty string")
	private String author;
	
	@NotBlank(message = "Title cannot be null nor empty string")
	private String title;
	
	private String description;
	
	@NotNull(message = "CreatedAt cannot be null")
	private Instant createdAt;
	
	@NotNull
	private Long location;
	
	@NotBlank
	private String status;
	
	@NotNull
	private Double longitude;
	
	@NotNull
	private Double latitude;
	
	private List<String >categories;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getLocation() {
		return location;
	}

	public void setLocation(Long location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	
}
