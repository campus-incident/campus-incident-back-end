package org.campusincident.webservice.exceptions;

public class ImageNotFoundException extends RuntimeException {

	public ImageNotFoundException(Long id) {
		super("could not find location " + id);
	}
	
}
