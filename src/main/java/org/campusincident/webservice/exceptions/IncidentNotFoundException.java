package org.campusincident.webservice.exceptions;

public class IncidentNotFoundException extends RuntimeException {

	public IncidentNotFoundException(Long id) {
		super("could not find incident " + id);
	}
	
}
