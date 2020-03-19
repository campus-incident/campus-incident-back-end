package org.campusincident.webservice.category;

import java.util.List;
import java.util.Optional;

import org.campusincident.webservice.exceptions.CategoryNameAlreadyExsistException;
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
	@Autowired
	private CategoryService servCategory;
	
	@GetMapping("/categories")
	public List<Category> getAllCategories(@RequestParam(required = false) Optional<String> containing) {
		
		Iterable<Category> categories = containing
				.map( x -> this.repoCategory.findByNameContaining(x) )
				.orElseGet( () -> this.repoCategory.findAll());
		
		return (List<Category>) categories;
	}
	
	@PostMapping("/categories")
	public Category newCategory(@RequestBody Category newCat) {
		Optional<Category> storedCat = this.repoCategory.findById(newCat.getName());
		if (storedCat.isPresent()) {
			throw new CategoryNameAlreadyExsistException(storedCat.get().getName());
		}
		return this.repoCategory.save(newCat);
	}
	
	@PostMapping("/categories/rename")
	public Category renameCategory(@RequestBody CategoryRenameDto renaming) {
		return this.servCategory.rename(renaming.getFrom(), renaming.getTo());
	}

}
