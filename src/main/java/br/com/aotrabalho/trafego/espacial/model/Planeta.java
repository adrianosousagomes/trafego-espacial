package br.com.aotrabalho.trafego.espacial.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Representa um planeta.
 * @author adriano.gomes
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Planeta implements Serializable{

	private static final long serialVersionUID = -7950840727224627843L;
	private String name;
	private String diameter;
	private String climate;
	private String terrain;
	private String population;
	
		
	public Planeta() {
		super();
	}

	public Planeta(String name, String diameter, String climate, String terrain,
			String population) {
		super();
		this.name = name;
		this.diameter = diameter;
		this.climate = climate;
		this.terrain = terrain;
		this.population = population;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDiameter() {
		return diameter;
	}
	public void setDiameter(String diameter) {
		this.diameter = diameter;
	}
	public String getClimate() {
		return climate;
	}
	public void setClimate(String climate) {
		this.climate = climate;
	}
	public String getTerrain() {
		return terrain;
	}
	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}
	public String getPopulation() {
		return population;
	}
	public void setPopulation(String population) {
		this.population = population;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((climate == null) ? 0 : climate.hashCode());
		result = prime * result
				+ ((diameter == null) ? 0 : diameter.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((population == null) ? 0 : population.hashCode());
		result = prime * result + ((terrain == null) ? 0 : terrain.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planeta other = (Planeta) obj;
		if (climate == null) {
			if (other.climate != null)
				return false;
		} else if (!climate.equals(other.climate))
			return false;
		if (diameter == null) {
			if (other.diameter != null)
				return false;
		} else if (!diameter.equals(other.diameter))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (population == null) {
			if (other.population != null)
				return false;
		} else if (!population.equals(other.population))
			return false;
		if (terrain == null) {
			if (other.terrain != null)
				return false;
		} else if (!terrain.equals(other.terrain))
			return false;
		return true;
	}
}
