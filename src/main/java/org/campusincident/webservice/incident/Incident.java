package org.campusincident.webservice.incident;

import java.time.Instant;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.campusincident.webservice.category.Category;
import org.campusincident.webservice.geolocation.Geolocation;
import org.campusincident.webservice.location.Location;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Incident {

	@Id @GeneratedValue
	private Long id;
	
	@ApiModelProperty(notes = "email", required = true)
	@NotBlank(message = "Author cannot be null nor empty string")
	private String author;
	
	@NotBlank(message = "Title cannot be null nor empty string")
	private String title;
	
	private String description;
	
	@NotNull(message = "CreatedAt cannot be null")
	private Instant createdAt;
	
	@ManyToOne
	@NotNull
	private Location location;
	
	@ManyToOne
	@NotNull
	private Geolocation geolocation;
	
	private String status;
	
	@ManyToMany
	private List<Category> categories;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Geolocation getGeolocation() {
		return geolocation;
	}

	public void setGeolocation(Geolocation geolocation) {
		this.geolocation = geolocation;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
}
