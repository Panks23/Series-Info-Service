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

        List<Series> getSeriesByRating(Double rating);

        List<Series> getSeriesByGenre(String genre);

        List<Series> getSeriesByRatingRange(Double fromRating, Double toRating);

        List<Series> getSeriesByFiltering(Double rating, String genre);

        void deleteAllSeries(List<Series> listOfSeries);
    }
