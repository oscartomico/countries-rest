package com.countries.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.countries.dto.Country;

/**
 * Interface created to use JPA Repository
 * @author Oscar Tomico
 *
 */
public interface CountryRepository extends JpaRepository<Country, Long> {
 
}
