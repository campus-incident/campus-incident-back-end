package org.campusincident.webservice.incident;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository extends CrudRepository<Incident, Long>{
	
	List<Incident> findByCategories_Name(String name);
	
}
