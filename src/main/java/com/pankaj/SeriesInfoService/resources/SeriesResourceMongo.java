package com.pankaj.SeriesInfoService.resources;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	
	@PostMapping
	@Cacheable(value="series", key="#series.id", unless = "#series.rating < 3")
	public Series addSeries(@RequestBody Series series) {
		return seriesMongoRepository.save(series);
	}
	
	@GetMapping
	public List<Series> getAll(){
		return seriesMongoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	@Cacheable(value="series", key="#id", unless = "#result.rating < 3")
	public Series getSeriesById(@PathVariable("id") final String id) {
		return seriesMongoRepository.findById(id).get();
	}
	
	
	@PutMapping
	@CachePut(key = "#series.id", value="series", unless="#series.rating < 3")
	public Series updateSeries(@RequestBody Series series) {
		return seriesMongoRepository.save(series);
	}
	
	@DeleteMapping("/{id}")
	@CacheEvict(value="series", key="#id")
	public List<Series> deleteSeriesById(@PathVariable("id") final String id){
		seriesMongoRepository.deleteById(id);
		return seriesMongoRepository.findAll();
	}
	
}
