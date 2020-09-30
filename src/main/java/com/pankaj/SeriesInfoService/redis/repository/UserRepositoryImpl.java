package com.pankaj.SeriesInfoService.redis.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.pankaj.SeriesInfoService.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository{
	
	private RedisTemplate<String, User> redisTemplate;
	
	private HashOperations<String, String, User> hashOperations;


	public UserRepositoryImpl(RedisTemplate<String, User> redisTemplate) {
		this.redisTemplate = redisTemplate;
		hashOperations = this.redisTemplate.opsForHash();
	}
	

	@Override
	public void save(User user) {
		hashOperations.put("USER", user.getId(), user);
	}

	@Override
	public Map<String, User> findAll() {
		return hashOperations.entries("USER");
	}

	@Override
	public User findById(String id) {
		return hashOperations.get("USER", id) ;
	}

	@Override
	public void update(User user) {
		save(user);
	}

	@Override
	public void delete(String id) {
		hashOperations.delete("USER", id);
	}

}
