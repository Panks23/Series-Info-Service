package com.pankaj.SeriesInfoService.service;


import com.pankaj.SeriesInfoService.dto.SeriesDTO;
import com.pankaj.SeriesInfoService.dto.SeriesResponseDTO;

import java.util.List;
import java.util.Optional;

public interface ISeriesService {

        void addSeries(SeriesDTO series);

        void addAllSeries(List<SeriesDTO> listOfSeries);

        List<SeriesDTO> getAllSeries();

        SeriesResponseDTO getAllSeriesByOffset(int pageNo, int size);

        Optional<SeriesDTO> getSeriesById( String id);

        SeriesDTO updateSeries(SeriesDTO SeriesDTO);

        void deleteSeriesById(String id);

        List<SeriesDTO> getAllSeriesSortedByRating(String sort, int pageNo, int size);

        List<SeriesDTO> getSeriesByRating(Double rating, int pageNo, int size);

        List<SeriesDTO> getSeriesByGenre(String genre, int pageNo, int size);

        SeriesResponseDTO getSeriesByRatingRange(Double fromRating, Double toRating, int pageNo, int size);

        List<SeriesDTO> getSeriesByFiltering(Double rating, String genre, int pageNo, int size);

        void deleteAllSeries(List<SeriesDTO> listOfSeries);
    }
