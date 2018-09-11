package com.countries.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.countries.dao.CountryRepository;
import com.countries.dto.Country;
/**
 * This class encapsulate all implementations of the service. Also it comunicate with database
 * with JPA Repository.
 * @author Oscar Tomico
 *
 */
@Service
public class CountryService {
	@Autowired
    CountryRepository repository;
	
	/**
	 * This method allow save and update countries
	 * @param entity country that you want to save or update
	 * @return the entity saved or updated
	 */
	public Country updateSave(Country country) {
		return repository.save(country);
	}
	
	/**
	 * This method allow get all countries from the database
	 * @return the list of countries
	 */
	public List<Country> list() {
		return repository.findAll();
	}
	
	/**
	 * This method allow find countries using the id
	 * @param id of the country that you wanto to find
	 * @return the country searched
	 */
	public Country findById(Long id) {
		return repository.findById(id).orElse(null);		
	}
	
	/**
	 * This method allow delete a country
	 * @param id of the country that you want to remove
	 */
	public void removeById(Long id) {
		repository.deleteById(id);
	}
}
