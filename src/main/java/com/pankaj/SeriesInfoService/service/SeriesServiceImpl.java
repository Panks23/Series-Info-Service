package com.pankaj.SeriesInfoService.service;

import com.pankaj.SeriesInfoService.model.Series;
import com.pankaj.SeriesInfoService.repository.SeriesMongoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class SeriesServiceImpl implements ISeriesService{

    private SeriesMongoRepository seriesMongoRepository;

    public SeriesServiceImpl(SeriesMongoRepository seriesMongoRepository) {
        this.seriesMongoRepository = seriesMongoRepository;
    }

    @Override
    public void addSeries(Series series) {
        seriesMongoRepository.save(series);
    }

    @Override
    public void addAllSeries(List<Series> listOfSeries) {
        seriesMongoRepository.saveAll(listOfSeries);
    }

    @Override
    public List<Series> getAllSeries() {
        return seriesMongoRepository.findAll();
    }

    @Override
    public List<Series> getAllSeriesByOffset(int pageNo, int size) {
        try {
            return seriesMongoRepository.findAll(PageRequest.of(pageNo, size)).getContent();
        }catch (IllegalArgumentException illegalArgumentException){
            return seriesMongoRepository.findAll(PageRequest.of(0, 3)).getContent();
        }
    }

    @Override
    public Optional<Series> getSeriesById(String id) {
            return seriesMongoRepository.findById(id);
    }

    @Override
    public Series updateSeries(Series series) {
        return seriesMongoRepository.save(series);
    }

    @Override
    public void deleteSeriesById(String id) {
        seriesMongoRepository.deleteById(id);
    }

    @Override
    public List<Series> getAllSeriesSortedByRating(String sort) {
        if(sort.equals("-rating")) {
            return seriesMongoRepository.findAll(Sort.by(Sort.Direction.DESC, "rating"));
        }else if(sort.equals("rating")){
            return seriesMongoRepository.findAll(Sort.by(Sort.Direction.ASC, "rating"));
        }else {
            return seriesMongoRepository.findAll();
        }
    }

    @Override
    public List<Series> getSeriesByRating(Double rating, int pageNo, int size) {
        return seriesMongoRepository.findByRating(rating, PageRequest.of(pageNo, size)).getContent();
    }

    @Override
    public List<Series> getSeriesByGenre(String genre, int pageNo, int size) {
        return seriesMongoRepository.findByGenre(genre, PageRequest.of(pageNo, size)).getContent();
    }

    @Override
    public List<Series> getSeriesByRatingRange(Double fromRating, Double toRating,int pageNo, int size) {
        return seriesMongoRepository.findByRatingBetween(fromRating, toRating, PageRequest.of(pageNo, size)).getContent();
    }

    @Override
    public List<Series> getSeriesByFiltering(Double rating, String genre, int pageNo, int size) {
        List<Series> listOfSeries = new ArrayList<>();
        listOfSeries.addAll(getSeriesByRating(rating, pageNo, size));
        listOfSeries.addAll(getSeriesByGenre(genre, pageNo, size));
        return listOfSeries;
    }

    @Override
    public void deleteAllSeries(List<Series> listOfSeries) {
        seriesMongoRepository.deleteAll(listOfSeries);
    }
}
