package com.pankaj.SeriesInfoService.resources;

import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pankaj.SeriesInfoService.model.Series;
import com.pankaj.SeriesInfoService.redis.repository.SeriesRepoImpl;

@RestController
@RequestMapping("/series")
public class SeriesResourceRedis {
	
	private SeriesRepoImpl seriesRepoImpl;
	
	public SeriesResourceRedis(SeriesRepoImpl seriesRepoImpl) {
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
	
	@GetMapping("/{id}")
	public Series getSeriesById(@PathVariable("id") final String id) {
		return seriesRepoImpl.findById(id);
	}
	
	@PutMapping("/update/{id}/{series}/{genre}")
	public void update(@PathVariable("id") final String id,
			@PathVariable("series") final String series,
			@PathVariable("genre") final String genre) {
		seriesRepoImpl.update(new Series(id, series, genre, 4.00, "Thrilling Show", 40));
	}
	
	@DeleteMapping("/delete/{id}")
	public Map<String, Series> deleteAll(@PathVariable("id") final String id) {
		return seriesRepoImpl.delete(id);
	}
	
	
	
}
