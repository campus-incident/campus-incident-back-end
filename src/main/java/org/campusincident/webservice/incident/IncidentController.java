package org.campusincident.webservice.incident;

import java.util.List;

import org.campusincident.webservice.category.CategoryService;
import org.campusincident.webservice.exceptions.IncidentNotFoundException;
import org.campusincident.webservice.exceptions.LocationNotFoundException;
import org.campusincident.webservice.geolocation.Geolocation;
import org.campusincident.webservice.geolocation.GeolocationRepository;
import org.campusincident.webservice.image.ImageService;
import org.campusincident.webservice.location.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IncidentController {

	@Autowired
	private IncidentRepository repoIncident;
	@Autowired
	private LocationRepository repoLocation;
	@Autowired
	private GeolocationRepository repoGeolocation;
	@Autowired
	private CategoryService servCategory;
	@Autowired
	private ImageService servImage;
	
	@GetMapping("/incidents")
	List<Incident> getAllIncidents() {
		return (List<Incident>) repoIncident.findAll();
	}
	
	@GetMapping("/incidents/{id}")
	Incident getLocation(@PathVariable Long id) {
		return this.repoIncident.findById(id).orElseThrow(() -> new IncidentNotFoundException(id));
	}
	
	@PostMapping("/incidents")
	@Transactional
	Incident newIncident(@RequestBody IncidentDto newIncident) {
		Incident tmp = new Incident();
		tmp.setAuthor(newIncident.getAuthor());
		tmp.setDescription(newIncident.getDescription());
		tmp.setLocation(this.repoLocation.findById(newIncident.getLocation()).orElseThrow(() -> new LocationNotFoundException(newIncident.getLocation())));
		tmp.setStatus(newIncident.getStatus());
		tmp.setCreatedAt(newIncident.getCreatedAt());
		tmp.setTitle(newIncident.getTitle());
		tmp.setGeolocation(
			repoGeolocation.findByLongitudeAndLatitude(
				newIncident.getLongitude(), newIncident.getLatitude()
			).orElseGet(() -> {
				Geolocation newGeo = new Geolocation();
				newGeo.setLatitude(newIncident.getLatitude());
				newGeo.setLongitude(newIncident.getLongitude());
				return this.repoGeolocation.save(newGeo);
			})
		);
		tmp.setCategories(this.servCategory.getOrCreate(newIncident.getCategories()));
		
		if (newIncident.getImageId() != null) {
			servImage.get(newIncident.getImageId()); // will throw if image not found
			tmp.setImageId(newIncident.getImageId());
		}
		
		return this.repoIncident.save(tmp);
	}
	
}
