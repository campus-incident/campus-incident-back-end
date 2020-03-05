package org.campusincident.webservice.category;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, String>{

	Iterable<Category> findByNameContaining(String name);
	
}
