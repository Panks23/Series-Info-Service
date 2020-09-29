package com.pankaj.SeriesInfoService.resources;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pankaj.SeriesInfoService.model.Series;
import com.pankaj.SeriesInfoService.model.User;
import com.pankaj.SeriesInfoService.repository.SeriesRepoImpl;

@RestController
@RequestMapping("/series")
public class SeriesResource {
	
	private SeriesRepoImpl seriesRepoImpl;
	
	public SeriesResource(SeriesRepoImpl seriesRepoImpl) {
		this.seriesRepoImpl = seriesRepoImpl;
	}
	
	@PostMapping("/add/{id}/{series}/{genre}")
	public void add(@PathVariable("id") final String id,
					@PathVariable("series") final String series,
					@PathVariable("genre") final String genre){
		seriesRepoImpl.save(new Series(id, series, genre, 4.00, "Chilling show", 20));
	}
	
	@GetMapping("/all")
	public Map<String, Series> findAll(){
		return seriesRepoImpl.findAll();
	}
	
}
