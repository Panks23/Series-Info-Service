package com.pankaj.SeriesInfoService.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pankaj.SeriesInfoService.model.Series;


public interface SeriesMongoRepository extends MongoRepository<Series, String> {

}
