package org.campusincident.webservice.location;

import java.util.List;

import org.campusincident.webservice.exceptions.LocationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {
	
	@Autowired
	private LocationRepository repoLocation;
	
	@GetMapping("/locations")
	public List<Location> getAllLocations() {
		return (List<Location>) repoLocation.findAll();
	}
	
	@GetMapping("/locations/{id}")
	public Location getLocation(@PathVariable Long id) {
		return this.repoLocation.findById(id).orElseThrow(() -> new LocationNotFoundException(id));
	}

}
