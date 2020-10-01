package com.pankaj.SeriesInfoService.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pankaj.SeriesInfoService.model.Series;


public interface SeriesMongoRepository extends MongoRepository<Series, String>, PagingAndSortingRepository<Series, String> {

	List<Series> findByRatingGreaterThan(Double rating);
	List<Series> findByRating(Double rating);
	List<Series> findByRatingBetween(Double fromRating, Double toRating);
	List<Series> findByGenre(String genre);
}
