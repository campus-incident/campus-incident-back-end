package org.campusincident.webservice.exceptions;

public class CategoryNotFoundException extends RuntimeException {

	public CategoryNotFoundException(String name) {
		super("could not find category " + name);
	}
	
}
