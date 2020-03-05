package org.campusincident.webservice.exceptions;

public class CategoryNameAlreadyExsistException extends RuntimeException {

	public CategoryNameAlreadyExsistException(String name) {
		super("Category name (" + name + ") already exsists");
	}
	
}
