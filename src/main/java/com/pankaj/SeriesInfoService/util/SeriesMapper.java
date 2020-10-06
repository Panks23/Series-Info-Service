package com.pankaj.SeriesInfoService.util;

import com.pankaj.SeriesInfoService.dto.SeriesDTO;
import com.pankaj.SeriesInfoService.dto.SeriesResponseDTO;
import com.pankaj.SeriesInfoService.model.Series;

import java.util.ArrayList;
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

    public static SeriesResponseDTO successListOfSeriesResponse(List<Series> listOfSeries){
        return new SeriesResponseDTO().setStatus(200).setMessage("You Got Your Response Bois")
                .setCount(0).setApi_element("Api_Element").setResult(toListSeriesDTO(listOfSeries));
    }

    public static SeriesResponseDTO errorListOfSeriesResponse(IllegalArgumentException illegalArgumentException){
        return new SeriesResponseDTO().setStatus(401).setMessage(illegalArgumentException.getMessage())
                .setCount(0).setApi_element("Api_Element").setResult(new ArrayList<>());
    }


}
