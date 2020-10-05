package com.pankaj.SeriesInfoService.service;

import com.pankaj.SeriesInfoService.model.Series;

import java.util.List;
import java.util.Optional;

public interface ISeriesService {

        void addSeries(Series series);

        void addAllSeries(List<Series> listOfSeries);

        List<Series> getAllSeries();

        List<Series> getAllSeriesByOffset(int pageNo, int size);

        Optional<Series> getSeriesById( String id);

        Series updateSeries(Series series);

        void deleteSeriesById(String id);

        List<Series> getAllSeriesSortedByRating(String sort);

        List<Series> getSeriesByRating(Double rating, int pageNo, int size);

        List<Series> getSeriesByGenre(String genre, int pageNo, int size);

        List<Series> getSeriesByRatingRange(Double fromRating, Double toRating, int pageNo, int size);

        List<Series> getSeriesByFiltering(Double rating, String genre, int pageNo, int size);

        void deleteAllSeries(List<Series> listOfSeries);
    }
