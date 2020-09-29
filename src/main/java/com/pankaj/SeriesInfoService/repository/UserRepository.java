package com.pankaj.SeriesInfoService.repository;

import java.util.Map;

import com.pankaj.SeriesInfoService.model.User;

public interface UserRepository {
	
	void save(User user);
	Map<String, User> findAll();
	User findById(String id);
	void update(User user);
	void delete(String id);

}
