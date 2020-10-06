package com.pankaj.SeriesInfoService.util;

import com.pankaj.SeriesInfoService.dto.SeriesDTO;
import com.pankaj.SeriesInfoService.model.Series;

import java.util.List;
import java.util.stream.Collectors;

public class SeriesMapper {

    public static SeriesDTO toSeriesDTO(Series series){
        return new SeriesDTO().setId(series.getId())
                .setName(series.getName())
                .setDescription(series.getDescription())
                .setGenre(series.getGenre())
                .setNumberOfSeason(series.getNumberOfSeason())
                .setRating(series.getRating());
    }

    public static Series toSeries(SeriesDTO seriesDTO){
        return new Series().setId(seriesDTO.getId())
                .setName(seriesDTO.getName())
                .setDescription(seriesDTO.getDescription())
                .setGenre(seriesDTO.getGenre())
                .setNumberOfSeason(seriesDTO.getNumberOfSeason())
                .setRating(seriesDTO.getRating());
    }

    public static List<SeriesDTO> toListSeriesDTO(List<Series> listOfSeries){
        List<SeriesDTO> listOfSeriesDTO = listOfSeries.stream().map(series ->
        toSeriesDTO(series)).collect(Collectors.toList());
        return listOfSeriesDTO;
    }


    public static List<Series> toListSeries(List<SeriesDTO> listOfSeriesDTO){
        List<Series> listOfSeries = listOfSeriesDTO.stream().map(series ->
                toSeries(series)).collect(Collectors.toList());
        return listOfSeries;
    }


}
