package org.campusincident.webservice.geolocation;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeolocationRepository extends CrudRepository<Geolocation, Long>{

	Optional<Geolocation> findByLongitudeAndLatitude(Double longitude, Double latitude);
	
}
