package com.pankaj.SeriesInfoService.resources;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pankaj.SeriesInfoService.model.User;
import com.pankaj.SeriesInfoService.repository.UserRepository;

@RestController
@RequestMapping("/rest/user")
public class UserResource {
	
	private UserRepository userRepository;
	
	
	public UserResource(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@PostMapping("/add/{id}/{name}")
	public void add(@PathVariable("id") final String id,
					@PathVariable("name") final String name){
		userRepository.save(new User(id, name, 20000L));
	}
	
	@GetMapping("/all")
	public Map<String, User> findAll(){
		return userRepository.findAll();
	}
	

}
