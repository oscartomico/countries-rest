package com.countries.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.countries.dto.Country;
import com.countries.dto.CountryRequest;
import com.countries.dto.CountryResponse;

/**
 * This class has API and minimal implementation (only data preparation)
 * @author Oscar Tomico
 *
 */
@CrossOrigin
@RestController
public class CountryController {
	@Autowired
	CountryService service;
	
	@Autowired
	Mapper mapper;
	
	/**
	 * This service allow save new countries
	 * @param countryRequest the request with country data
	 * @return response with status code (ok) and country data
	 */
	@RequestMapping(value = "/countries", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody @Valid CountryRequest countryRequest){
		Country country = mapper.map(countryRequest, Country.class);
		Country countryStored = service.updateSave(country);
        CountryResponse countryResponse = mapper.map(countryStored, CountryResponse.class);
        return ResponseEntity.ok(countryResponse);
    }
	
	/**
	 * This service allow get all countries in database
	 * @return response with status code (ok) and the list of countries
	 */
	@RequestMapping(value = "/countries")
	public ResponseEntity listCountries() {
		List<Country> countries = service.list();
		if (countries.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(countries.stream().map(country -> mapper.map(country, CountryResponse.class)).collect(Collectors.toList()));
	}
	
	/**
	 * This service allow search a country using its id
	 * @param id of the country that you want to find
	 * @return response with status (ok or not found) and, if id exists, country data
	 */
	@RequestMapping(value = "/countries/{id}")
	public ResponseEntity getById(@PathVariable Long id) {				
		Country country = service.findById(id);
		if (country == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(mapper.map(country, CountryResponse.class));
	}
	
	/**
	 * This service allow edit country data
	 * @param id of the country that you want to update
	 * @param id of the country that you want to update and countryRequest with new country data 
	 * @return response with status code (ok, not found or bad request) and, if ok, country data updated
	 */
	@RequestMapping(value = "/countries/{id}", method = RequestMethod.PUT)
	public ResponseEntity editById(@PathVariable Long id, @RequestBody @Valid CountryRequest countryRequest) {
		// First find country by id
		Country countryFinded = service.findById(id);
		
		if (countryFinded == null) {
			return ResponseEntity.notFound().build();
		}
		
		if (id != countryRequest.getId()) {
			return ResponseEntity.badRequest().build();
		}
		
		// Map the request country
		Country country = mapper.map(countryRequest, Country.class);
		
		// Update country
		service.updateSave(country);
		
		return ResponseEntity.ok(mapper.map(country, CountryResponse.class));
	}
	
	/**
	 * This service allow remove a country
	 * @param id of the country that you want to delete
	 * @return response with the status code (ok or not found)
	 */
	@RequestMapping(value = "/countries/{id}", method = RequestMethod.DELETE)
	public ResponseEntity removeById(@PathVariable Long id) {
		// First find the country
		Country country = service.findById(id);
		
		// Check if id not exists
		if (country == null) {
			return ResponseEntity.notFound().build();
		}
		service.removeById(id);
		return ResponseEntity.ok(mapper.map(country, CountryResponse.class));
	}
}
