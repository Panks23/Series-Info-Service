package com.pankaj.SeriesInfoService.repository;

import java.util.Map;

import com.pankaj.SeriesInfoService.model.Series;
import com.pankaj.SeriesInfoService.model.User;

public interface SeriesRepo {
	
	void save(Series series);
	Map<String, Series> findAll();
	Series findById(String id);
	void update(Series series);
	void delete(String id);

}
