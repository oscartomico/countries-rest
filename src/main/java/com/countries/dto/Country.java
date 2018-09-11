package com.countries.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity for JPA Repository, it's internal for REST Service and clients can't access it
 * @author Oscar Tomico
 *
 */
@Entity
@Table(name = "countries")
public class Country implements Serializable {
	@Id
	@GeneratedValue
	Long id;
	String name;
	int population;	
	
	public Country() {
		
	}
	
	public Country(Long id, String name, int population) {
		super();
		this.id = id;
		this.name = name;
		this.population = population;
	}
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
