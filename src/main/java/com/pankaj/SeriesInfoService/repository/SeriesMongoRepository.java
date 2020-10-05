package com.pankaj.SeriesInfoService.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pankaj.SeriesInfoService.model.Series;


public interface SeriesMongoRepository extends MongoRepository<Series, String>{

	List<Series> findByRatingGreaterThan(Double rating);

	List<Series> findByRating(Double rating);

	Page<Series> findByRatingBetween(Double fromRating, Double toRating, Pageable page);

	Page<Series> findByGenre(String genre, Pageable page);

	Page<Series> findByRating(Double rating, Pageable page);

}
