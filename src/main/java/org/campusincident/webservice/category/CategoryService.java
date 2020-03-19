package org.campusincident.webservice.category;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.campusincident.webservice.exceptions.CategoryNameAlreadyExsistException;
import org.campusincident.webservice.exceptions.CategoryNotFoundException;
import org.campusincident.webservice.geolocation.GeolocationRepository;
import org.campusincident.webservice.incident.Incident;
import org.campusincident.webservice.incident.IncidentRepository;
import org.campusincident.webservice.location.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	@Autowired private IncidentRepository repoIncident;
	@Autowired private LocationRepository repoLocation;
	@Autowired private GeolocationRepository repoGeolocation;
	@Autowired private CategoryRepository repoCategory;
	
	public Category getOrCreate(String name) {
		return this.repoCategory.findById(name).orElseGet(() -> {
			Category cat = new Category();
			cat.setName(name);
			return this.repoCategory.save(cat);
		});
	}
	
	/** creates only non existing names, otherwise will return the existing Category */
	public List<Category> getOrCreate(List<String> names) {
		if(names == null) return new ArrayList<>();
		return names.stream().map(this::getOrCreate).collect(Collectors.toList());
	}

	public Category rename(String from, String to) {
		Category old = this.repoCategory.findById(from).orElseThrow(() -> new CategoryNotFoundException(from));
		if (this.repoCategory.findById(to).isPresent()) {
			throw new CategoryNameAlreadyExsistException(to);
		}
		
		Category newCat = new Category();
		newCat.setName(to);
		newCat = this.repoCategory.save(newCat);
		
		// cascade entities
		List<Incident> incidentList = this.repoIncident.findByCategories_Name(from);
		for (Incident incident : incidentList) {
			incident.getCategories().remove(old);
			incident.getCategories().add(newCat);
			this.repoIncident.save(incident);
		}
		
		this.repoCategory.delete(old);

		return newCat;
	}
	
}
