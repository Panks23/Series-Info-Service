package com.pankaj.SeriesInfoService.redis.repository;

import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.pankaj.SeriesInfoService.model.Series;


@Repository
public class SeriesRepoImpl implements SeriesRepo {
   
	
	private RedisTemplate<String, Series> redisTemplateSeries;
	
	private HashOperations<String, String, Series> hashOperations;
	
	public SeriesRepoImpl(RedisTemplate<String, Series> redisTemplate) {
		this.redisTemplateSeries = redisTemplate;
		hashOperations = redisTemplateSeries.opsForHash();
	}
	
	
	@Override
	public void save(Series series) {
		hashOperations.put("SERIES", series.getId(), series);
	}

	@Override
	public Map<String, Series> findAll() {
		return hashOperations.entries("SERIES");
	}

	@Override
	public Series findById(String id) {
		return hashOperations.get("SERIES", id);
	}

	@Override
	public void update(Series series) {
		save(series);
	}

	@Override
	public Map<String, Series> delete(String id) {
		hashOperations.delete("SERIES", id);
		return hashOperations.entries("SERIES");
	}

}
