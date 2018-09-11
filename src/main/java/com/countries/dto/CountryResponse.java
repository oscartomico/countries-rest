package com.countries.dto;

/**
 * Class with data for the clients
 * @author Oscar Tomico
 *
 */
public class CountryResponse {
	Long id;
	String name;
	int population;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
}
