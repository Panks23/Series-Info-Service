package com.pankaj.SeriesInfoService.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.pankaj.SeriesInfoService.service.ISeriesService;
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
import com.pankaj.SeriesInfoService.repository.SeriesMongoRepository;

@RestController
@RequestMapping("/mongo")
public class SeriesResourceMongo {
	
	private ISeriesService seriesService;
	
	public SeriesResourceMongo(ISeriesService seriesService) {
		this.seriesService = seriesService;
	}

	
	@PostMapping("/series")
	public void addSeries(@RequestBody Series series) {
		seriesService.addSeries(series);
	}

	@PostMapping("/series/all")
	public void addAllSeries(@RequestBody List<Series> listOfSeries){
		seriesService.addAllSeries(listOfSeries);
	}
	
	@GetMapping("/series")
	public List<Series> getAllSeries(){
		return seriesService.getAllSeries();
	}
	
	@GetMapping(path="/series", params={"pageNo", "size"})
	public List<Series>  getAllSeriesByOffset(@RequestParam("pageNo") final int pageNo, @RequestParam("size") final int size){
		return seriesService.getAllSeriesByOffset(pageNo, size);
	}
	
	@GetMapping("/series/{id}")
	@Cacheable(value="series", key="#id", unless = "#result.rating < 3")
	public Optional<Series> getSeriesById(@PathVariable("id") final String id) {
		return seriesService.getSeriesById(id);
	}
	
	
	@PutMapping("/series")
	@CachePut(key = "#series.id", value="series", unless="#series.rating < 3")
	public Series updateSeries(@RequestBody Series series) {
		return seriesService.updateSeries(series);
	}
	
	@DeleteMapping("/series/{id}")
	@CacheEvict(value="series", key="#id")
	public void  deleteSeriesById(@PathVariable("id") final String id){
		seriesService.deleteSeriesById(id);
	}
	
	@GetMapping(path= "/series", params="sort")
	public List<Series> getAllSeriesSortedByRating(@RequestParam("sort") final String sort){
		return seriesService.getAllSeriesSortedByRating(sort);
	}
	
	@GetMapping(path="/series", params={"genre", "pageNo", "size"})
	public List<Series> getSeriesByGenre(@RequestParam("genre") final String genre,
										 @RequestParam("pageNo") int pageNo, @RequestParam("size") int size){
		return seriesService.getSeriesByGenre(genre, pageNo, size);
	}
	
	@GetMapping(path="/series", params={"rating", "pageNo", "size"})
	public List<Series> getSeriesByRating(@RequestParam("rating") final Double rating,
										  @RequestParam("pageNo") int pageNo,  @RequestParam("size") int size){
		return seriesService.getSeriesByRating(rating, pageNo, size);
	}

	@GetMapping(path="/series", params={"fromRating","toRating", "pageNo", "size"})
	public List<Series> getSeriesByRatingRange(@RequestParam("fromRating") final Double fromRating,
			@RequestParam("toRating") final Double toRating,
			@RequestParam("pageNo") int pageNo, @RequestParam("size") int size){
		return seriesService.getSeriesByRatingRange(fromRating, toRating, pageNo, size);
	}
	
	@GetMapping(path="/series", params= {"rating", "genre", "pageNo", "size"})
	public List<Series> getSeriesByFiltering(@RequestParam("rating") final Double rating,
			@RequestParam("genre") final String genre,
	 		@RequestParam("pageNo") int pageNo, @RequestParam("size") int size){
		return seriesService.getSeriesByFiltering(rating, genre, pageNo, size);
	}

	@DeleteMapping("/series/delete/all")
	@CacheEvict(value="series", allEntries = true)
	public void deleteAllSeries(@RequestBody List<Series> listOfSeries){
		seriesService.deleteAllSeries(listOfSeries);
	}
	
	@GetMapping(path="/series/async", params= {"rating", "genre", "pageNo", "size"})
	public List<Series> getSeriesByFilteringAsync(@RequestParam("rating") final Double rating,
			@RequestParam("genre") final String genre, @RequestParam("pageNo") int pageNo, @RequestParam("size") int size){
		List<Series> listOfSeries = new ArrayList<>();
		Future<List<Series>> futureRating = CompletableFuture.supplyAsync(
				() -> {return seriesService.getSeriesByRating(rating, pageNo, size);});
		Future<List<Series>> futureGenre = CompletableFuture.supplyAsync(
				() -> {return seriesService.getSeriesByGenre(genre, pageNo, size);});
		try {
			listOfSeries.addAll(futureRating.get());
			listOfSeries.addAll(futureGenre.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listOfSeries;
	}
}
