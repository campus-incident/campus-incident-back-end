package org.campusincident.webservice.category;

import javax.validation.constraints.NotBlank;

public class CategoryRenameDto {
	
	@NotBlank
	private String from;
	
	@NotBlank
	private String to;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
	
}
