package com.countries;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.countries.api.CountryService;
import com.countries.dao.CountryRepository;
import com.countries.dto.Country;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CountryServiceTest {
	@Mock
	CountryRepository repository;
	
	@InjectMocks
	CountryService service;

	@Test
	public void updateSaveTest() {
		Country country = new Country(null, "SpainTest", 1000000);
		Country expectedCountry = new Country(1L, "SpainTest", 1000000);
		when(repository.save(country)).thenReturn(expectedCountry);
		Country storedCountry = service.updateSave(country);
		Assert.assertEquals(expectedCountry.getId(), storedCountry.getId());
	}
	
	@Test
	public void listTest() {
		// Choose a example five of mock countries
		List<Country> expectedCountries = new ArrayList<Country>();
		int numCountries = 5;
		for (int i = 0; i < numCountries; i++) {
			expectedCountries.add(new Country(null, "Spain" + i, 1000000));
		}
		// Coditions for the mock behaviur
		when(repository.findAll()).thenReturn(expectedCountries);
		
		// Get all countries
		List<Country> storedCountries = service.list();
		Assert.assertEquals(storedCountries.size(), numCountries);
	}
}
