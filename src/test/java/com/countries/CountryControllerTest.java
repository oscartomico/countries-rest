package com.countries;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.dozer.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.countries.api.CountryController;
import com.countries.api.CountryService;
import com.countries.dto.Country;
import com.countries.dto.CountryRequest;
import com.countries.dto.CountryResponse;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CountryControllerTest {
	@Mock
	Mapper mapper;
	
	@Mock
	CountryService service;
	
	@InjectMocks
	CountryController controller;
	
	// Create a Country Request
	private CountryRequest buildRequest() {
		CountryRequest request = new CountryRequest();
		request.setName("Spain");
		request.setPopulation(1000000);
		return request;
	}
	
	// Convert to CountryRequest to Country
	private Country requestToCountry(CountryRequest request) {
		return new Country(null, request.getName(), request.getPopulation());
	}
	
	@Test
	public void saveTest() {
		// Create the CountryRequest
		CountryRequest request = buildRequest();
		Country country = requestToCountry(request);
		// Mock conditions
		when(mapper.map(request, Country.class)).thenReturn(country);
		when(service.updateSave(country)).thenReturn(country);		
		ResponseEntity response = controller.save(request);
		// Check if is expected result
		Assert.assertEquals(response.getStatusCodeValue(), 200);
	}
	
	@Test
	public void listTest() {
		// Create new countries list
		List<Country> countries = new ArrayList<Country>();
		countries.add(new Country(1L, "Spain", 1000000));
		countries.add(new Country(1L, "France", 1000000));
		countries.add(new Country(1L, "Germany", 1000000));
		
		// Mock conditions
		when(service.list()).thenReturn(countries);
		
		ResponseEntity response = controller.listCountries();
		Assert.assertEquals(response.getStatusCodeValue(), 200);
	}
	
	@Test
	public void getByIdTest() {
		// Id
		Long id = 7L;
		
		//Mock conditions
		when(service.findById(id)).thenReturn(new Country(7L, "Spain", 1000000));
		
		ResponseEntity response = controller.getById(id);
		
		// If founded country		
		Assert.assertEquals(response.getStatusCodeValue(), 200);
		
		// If not founded
		ResponseEntity response404 = controller.getById(0L);
		Assert.assertEquals(response404.getStatusCodeValue(), 404);
	}
	
	@Test
	public void editByIdTest() {
		// Id
		Long id = 7L;
		
		// Build the request
		CountryRequest request = buildRequest();
		// Change the request id because we need to know id are the same
		request.setId(id);
				
		Country country = requestToCountry(request);
		
		// Mock conditions
		when(service.findById(id)).thenReturn(country);
		when(service.updateSave(country)).thenReturn(country);
		
		ResponseEntity response = controller.editById(id, request);
			
		// When the id are the same, the response status must to be 200
		Assert.assertEquals(response.getStatusCodeValue(), 200);
		
		
		// If the ID not exists then response has code not found 404
		ResponseEntity response404 = controller.editById(20L, request);
		
		Assert.assertEquals(response404.getStatusCodeValue(), 404);
		
		
			
		// However, if the id ism't the same that request, return 400, bad request
		request.setId(10L);
		ResponseEntity response400 = controller.editById(id, request);
		Assert.assertEquals(response400.getStatusCodeValue(), 400);	
	}
	
	@Test
	public void removeByIdTest() {
		// Id
		Long id = 7L;
		Country country = new Country(id, "Spain", 1000000);
		
		// Mock conditions
		when(service.findById(id)).thenReturn(country);
		
		// If the id exists then response is code 200
		ResponseEntity response = controller.removeById(id);
		Assert.assertEquals(response.getStatusCodeValue(), 200);
		
		// However, if id not exists, reponse will has code 404, not found
		ResponseEntity response404 = controller.removeById(10L);
		Assert.assertEquals(response404.getStatusCodeValue(), 404);		
	}
}
