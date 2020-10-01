package com.pankaj.SeriesInfoService.redis.repository;

import java.util.Map;

import com.pankaj.SeriesInfoService.model.Series;

public interface SeriesRepo {
	
	void save(Series series);
	Map<String, Series> findAll();
	Series findById(String id);
	void update(Series series);
	Map<String, Series> delete(String id);

}
