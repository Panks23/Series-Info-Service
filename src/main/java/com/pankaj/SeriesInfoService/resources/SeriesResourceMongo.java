package com.pankaj.SeriesInfoService.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pankaj.SeriesInfoService.model.Series;
import com.pankaj.SeriesInfoService.mongo.repository.SeriesMongoRepository;

@RestController
@RequestMapping("/mongo")
public class SeriesResourceMongo {
	
	private SeriesMongoRepository seriesMongoRepository;
	
	public SeriesResourceMongo(SeriesMongoRepository seriesMongoRepository) {
		this.seriesMongoRepository = seriesMongoRepository;
	}
	
	
	@PostMapping("/series")
	@Cacheable(value="series", key="#series.id", unless = "#series.rating < 3")
	public Series addSeries(@RequestBody Series series) {
		return seriesMongoRepository.save(series);
	}
	
	@GetMapping("/series")
	public List<Series> getAllSeries(){
		return seriesMongoRepository.findAll();
	}
	
	@GetMapping(path="/series", params={"pageNo", "size"})
	public List<Series>  getAllSeriesByOffset(@RequestParam("pageNo") final int pageNo, @RequestParam("size") final int size){
		return seriesMongoRepository.findAll(PageRequest.of(pageNo, size)).getContent();	
	}
	
	@GetMapping("/series/{id}")
	@Cacheable(value="series", key="#id", unless = "#result.rating < 3")
	public Series getSeriesById(@PathVariable("id") final String id) {
		return seriesMongoRepository.findById(id).get();
	}
	
	
	@PutMapping("/series")
	@CachePut(key = "#series.id", value="series", unless="#series.rating < 3")
	public Series updateSeries(@RequestBody Series series) {
		return seriesMongoRepository.save(series);
	}
	
	@DeleteMapping("/series/{id}")
	@CacheEvict(value="series", key="#id")
	public List<Series> deleteSeriesById(@PathVariable("id") final String id){
		seriesMongoRepository.deleteById(id);
		return seriesMongoRepository.findAll();
	}
	
	@GetMapping(path= "/series", params="sort")
	public List<Series> getAllSeriesSortedByRatingInDesc(@RequestParam("sort") final String sort){
		if(sort.equals("-rating")) {
			return seriesMongoRepository.findAll(Sort.by(Sort.Direction.DESC, "rating"));
		}else if(sort.equals("rating")){
			return seriesMongoRepository.findAll(Sort.by(Sort.Direction.ASC, "rating"));
		}else {
			return seriesMongoRepository.findAll();
		}
	}
	
	@GetMapping(path="/series", params="genre")
	public List<Series> getSeriesByGenre(@RequestParam("genre") final String genre){
		return seriesMongoRepository.findByGenre(genre);
	}
	
	@GetMapping(path="/series", params="rating")
	public List<Series> getSeriesByRating(@RequestParam("rating") final Double rating){
		return seriesMongoRepository.findByRating(rating);
	}
	
	@GetMapping(path="/series", params={"fromRating","toRating"})
	public List<Series> getSeriesByRatingRange(@RequestParam("fromRating") final Double fromRating,
			@RequestParam("toRating") final Double toRating){
		return seriesMongoRepository.findByRatingBetween(fromRating, toRating);
	}
	
	@GetMapping(path="/series", params= {"rating", "genre"})
	public List<Series> getSeriesByFiltering(@RequestParam("rating") final Double rating,
			@RequestParam("genre") final String genre){
		List<Series> listOfSeries = new ArrayList<>();
		listOfSeries.addAll(getSeriesByRating(rating));
		listOfSeries.addAll(getSeriesByGenre(genre));
		return listOfSeries;
	}
	
	
}
