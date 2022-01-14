package com.iata.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iata.model.Airports;
import com.iata.model.Iata;

public interface AirportsRepository extends CrudRepository<Airports, String>{
	 List<Airports> findByCityNameIgnoreCaseContaining( String name);
	 Airports findByCityName(String city);
	 List<Airports> findByCodeIn(List<String> code);
}
