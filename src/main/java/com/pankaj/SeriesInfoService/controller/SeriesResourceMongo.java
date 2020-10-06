package com.pankaj.SeriesInfoService.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.pankaj.SeriesInfoService.dto.SeriesDTO;
import com.pankaj.SeriesInfoService.dto.SeriesResponseDTO;
import com.pankaj.SeriesInfoService.service.ISeriesService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/mongo")
public class SeriesResourceMongo {
	
	private ISeriesService seriesService;
	
	public SeriesResourceMongo(ISeriesService seriesService) {
		this.seriesService = seriesService;
	}

	
	@PostMapping("/series")
	public void addSeries(@RequestBody SeriesDTO series) {
		seriesService.addSeries(series);
	}

	@PostMapping("/series/all")
	public void addAllSeries(@RequestBody List<SeriesDTO> listOfSeries){
		seriesService.addAllSeries(listOfSeries);
	}
	
	@GetMapping("/series")
	public List<SeriesDTO> getAllSeries(){
		return seriesService.getAllSeries();
	}

	@GetMapping(path="/series", params={"pageNo", "size"})
	public SeriesResponseDTO getAllSeriesByOffset(@RequestParam("pageNo") final int pageNo, @RequestParam("size") final int size){
		return seriesService.getAllSeriesByOffset(pageNo, size);
	}
	
	@GetMapping("/series/{id}")
	@Cacheable(value="series", key="#id", unless = "#result.rating < 3")
	public Optional<SeriesDTO> getSeriesById(@PathVariable("id") final String id) {
		return seriesService.getSeriesById(id);
	}
	
	
	@PutMapping("/series")
	@CachePut(key = "#SeriesDTO.id", value="series", unless="#SeriesDTO.rating < 3")
	public SeriesDTO updateSeries(@RequestBody SeriesDTO SeriesDTO) {
		return seriesService.updateSeries(SeriesDTO);
	}
	
	@DeleteMapping("/series/{id}")
	@CacheEvict(value="series", key="#id")
	public void  deleteSeriesById(@PathVariable("id") final String id){
		seriesService.deleteSeriesById(id);
	}

	//TODO Add pagination and handle exception where ever we add pagination

	@GetMapping(path= "/series", params={"sort", "pageNo", "size"})
	public List<SeriesDTO> getAllSeriesSortedByRating(@RequestParam("sort") final String sort,
													  @RequestParam("pageNo") int pageNo, @RequestParam("size") int size){
		return seriesService.getAllSeriesSortedByRating(sort, pageNo, size);
	}
	
	@GetMapping(path="/series", params={"genre", "pageNo", "size"})
	public List<SeriesDTO> getSeriesByGenre(@RequestParam("genre") final String genre,
										 @RequestParam("pageNo") int pageNo, @RequestParam("size") int size){
		return seriesService.getSeriesByGenre(genre, pageNo, size);
	}
	
	@GetMapping(path="/series", params={"rating", "pageNo", "size"})
	public List<SeriesDTO> getSeriesByRating(@RequestParam("rating") final Double rating,
											 @RequestParam("pageNo") int pageNo, @RequestParam("size") int size){
		return seriesService.getSeriesByRating(rating, pageNo, size);
	}

	@GetMapping(path="/series", params={"fromRating","toRating", "pageNo", "size"})
	public List<SeriesDTO> getSeriesByRatingRange(@RequestParam("fromRating") final Double fromRating,
			@RequestParam("toRating") final Double toRating,
			@RequestParam("pageNo") int pageNo, @RequestParam("size") int size){
		return seriesService.getSeriesByRatingRange(fromRating, toRating, pageNo, size);
	}
	
	@GetMapping(path="/series", params= {"rating", "genre", "pageNo", "size"})
	public List<SeriesDTO> getSeriesByFiltering(@RequestParam("rating") final Double rating,
			@RequestParam("genre") final String genre,
	 		@RequestParam("pageNo") int pageNo, @RequestParam("size") int size){
		return seriesService.getSeriesByFiltering(rating, genre, pageNo, size);
	}

	@DeleteMapping("/series/delete/all")
	@CacheEvict(value="series", allEntries = true)
	public void deleteAllSeries(@RequestBody List<SeriesDTO> listOfSeries){
		seriesService.deleteAllSeries(listOfSeries);
	}
	
//	@GetMapping(path="/series/async", params= {"rating", "genre", "pageNo", "size"})
//	public List<SeriesDTO> getSeriesByFilteringAsync(@RequestParam("rating") final Double rating,
//			@RequestParam("genre") final String genre, @RequestParam("pageNo") int pageNo, @RequestParam("size") int size){
//		List<SeriesDTO> listOfSeries = new ArrayList<>();
//		Future<List<SeriesDTO>> futureRating = CompletableFuture.supplyAsync(
//				() -> {return seriesService.getSeriesByRating(rating, pageNo, size);});
//		Future<List<SeriesDTO>> futureGenre = CompletableFuture.supplyAsync(
//				() -> {return seriesService.getSeriesByGenre(genre, pageNo, size);});
//		try {
//			listOfSeries.addAll(futureRating.get());
//			listOfSeries.addAll(futureGenre.get());
//		} catch (InterruptedException | ExecutionException e) {
//			e.printStackTrace();
//		}
//		return listOfSeries;
//	}
}
