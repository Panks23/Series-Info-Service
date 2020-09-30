package com.pankaj.SeriesInfoService.resources;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pankaj.SeriesInfoService.model.User;
import com.pankaj.SeriesInfoService.mongo.repository.UsersRepository;
import com.pankaj.SeriesInfoService.redis.repository.UserRepository;

@RestController
@RequestMapping("/rest/user")
public class UserResource {
	
	private UserRepository userRepository;
	
	private UsersRepository usersRepository;
	
	public UserResource(UserRepository userRepository, UsersRepository usersRepository) {
		this.userRepository = userRepository;
		this.usersRepository = usersRepository;
	}
	
	@PostMapping("/add/{id}/{name}")
	@Cacheable(value="users", key="#id")
	public void add(@PathVariable("id") final String id,
					@PathVariable("name") final String name){
		userRepository.save(new User(id, name, 20000L));
	}
	
	@GetMapping("/all")
	public Map<String, User> findAll(){
		return userRepository.findAll();
	}
	
	@GetMapping("/{id}")
	@Cacheable(value="users", key="#id")
	public User getUserById(@PathVariable("id") final String id) {
		return userRepository.findById(id);
	}
	
	@GetMapping("/mongo/all")
	public List<User> getAll(){
		return usersRepository.findAll();
	}
	
	@PostMapping("/mongo/add/{id}/{name}")
	public void addUser(@PathVariable("id") final String id,
						@PathVariable("name") final String name) {
		usersRepository.save(new User(id, name,  20000L));
	}
	

}
