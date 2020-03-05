package org.campusincident.webservice.exceptions;

public class LocationNotFoundException extends RuntimeException {

	public LocationNotFoundException(Long id) {
		super("could not find location " + id);
	}
	
}
