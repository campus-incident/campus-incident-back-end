package org.campusincident.webservice.category;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Category {
	
	@Id
	@NotBlank(message = "Name cannot be null or blank")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
