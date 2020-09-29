package com.pankaj.SeriesInfoService.model;

import java.io.Serializable;

public class Series implements Serializable {
	
	private String id;
	private String name;
	private String genre;
	private Double rating;
	private String description;
	private int numberOfSeason;
	
	
	
	public Series(String id, String name, String genre, Double rating, String description, int numberOfSeason) {
		this.id = id;
		this.name = name;
		this.genre = genre;
		this.rating = rating;
		this.description = description;
		this.numberOfSeason = numberOfSeason;
	}
	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNumberOfSeason() {
		return numberOfSeason;
	}
	public void setNumberOfSeason(int numberOfSeason) {
		this.numberOfSeason = numberOfSeason;
	}
	
	

}
