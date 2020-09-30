package com.pankaj.SeriesInfoService.resources;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pankaj.SeriesInfoService.model.Series;
import com.pankaj.SeriesInfoService.mongo.repository.SeriesMongoRepository;

@RestController
@RequestMapping("/mongo/series")
public class SeriesResourceMongo {
	
	private SeriesMongoRepository seriesMongoRepository;
	
	public SeriesResourceMongo(SeriesMongoRepository seriesMongoRepository) {
		this.seriesMongoRepository = seriesMongoRepository;
	}
	
	
	@PostMapping("/add/{id}/{series}/{genre}")
	public void addUser(@PathVariable("id") final String id,
						@PathVariable("series") final String series,
						@PathVariable("genre") final String genre) {
		seriesMongoRepository.save(new Series(id, series, genre, 4.00, "Chilling show", 20));
	}
	
	@GetMapping("/all")
	public List<Series> getAll(){
		return seriesMongoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Series getSeriesById(@PathVariable("id") final String id) {
		return seriesMongoRepository.findById(id).get();
	}
	
	
	@PutMapping("/add/{id}/{series}/{genre}")
	public void updateUser(@PathVariable("id") final String id,
						@PathVariable("series") final String series,
						@PathVariable("genre") final String genre) {
		seriesMongoRepository.save(new Series(id, series, genre, 4.00, "Best Sci-Fi", 20));
	}
	
	@DeleteMapping("/delete/{id}")
	public List<Series> deleteSeriesById(@PathVariable("id") final String id){
		seriesMongoRepository.deleteById(id);
		return seriesMongoRepository.findAll();
	}
}
