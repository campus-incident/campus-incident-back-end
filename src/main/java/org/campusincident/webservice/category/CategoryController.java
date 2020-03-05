package org.campusincident.webservice.category;

import java.util.List;
import java.util.Optional;

import org.campusincident.webservice.exceptions.CategoryNameAlreadyExsistException;
import org.campusincident.webservice.exceptions.CategoryNotFoundException;
import org.campusincident.webservice.incident.Incident;
import org.campusincident.webservice.incident.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryRepository repoCategory;
	@Autowired
	private IncidentRepository repoIncident;
	
	public CategoryController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/categories")
	List<Category> getAllCategories(@RequestParam Optional<String> containing) {
		
		Iterable<Category> categories = containing
				.map( x -> this.repoCategory.findByNameContaining(x) )
				.orElseGet( () -> this.repoCategory.findAll());
		
		return (List<Category>) categories;
	}
	
	@PostMapping("/categories")
	Category newCategory(@RequestBody Category newCat) {
		Optional<Category> storedCat = this.repoCategory.findById(newCat.getName());
		if (storedCat.isPresent()) {
			throw new CategoryNameAlreadyExsistException(storedCat.get().getName());
		}
		return this.repoCategory.save(newCat);
	}
	
	@PostMapping("/categories/rename")
	Category renameCategory(@RequestBody CategoryRenameDto renaming) {
		
		Category from = this.repoCategory.findById(renaming.getFrom()).orElseThrow(() -> new CategoryNotFoundException(renaming.getFrom()));
		if (this.repoCategory.findById(renaming.getTo()).isPresent()) {
			throw new CategoryNameAlreadyExsistException(renaming.getTo());
		}
		
		Category newCat = new Category();
		newCat.setName(renaming.getTo());
		newCat = this.repoCategory.save(newCat);
		
		// cascade entities
		List<Incident> incidentList = this.repoIncident.findByCategories_Name(renaming.getFrom());
		for (Incident incident : incidentList) {
			incident.getCategories().remove(from);
			incident.getCategories().add(newCat);
			this.repoIncident.save(incident);
		}
		
		this.repoCategory.delete(from);

		return newCat;
	}

}