package com.pankaj.SeriesInfoService.service;

import com.pankaj.SeriesInfoService.dto.SeriesDTO;
import com.pankaj.SeriesInfoService.dto.SeriesResponseDTO;
import com.pankaj.SeriesInfoService.model.Series;
import com.pankaj.SeriesInfoService.repository.SeriesMongoRepository;
import com.pankaj.SeriesInfoService.util.SeriesMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SeriesServiceImpl implements ISeriesService{

    private SeriesMongoRepository seriesMongoRepository;

    public SeriesServiceImpl(SeriesMongoRepository seriesMongoRepository) {
        this.seriesMongoRepository = seriesMongoRepository;
    }

    @Override
    public void addSeries(SeriesDTO seriesDTO) {
        seriesMongoRepository.save(SeriesMapper.toSeries(seriesDTO));
    }

    @Override
    public void addAllSeries(List<SeriesDTO> listOfSeriesDTO) {
        seriesMongoRepository.saveAll(SeriesMapper.toListSeries(listOfSeriesDTO));
    }

    @Override
    public List<SeriesDTO> getAllSeries() {
        return SeriesMapper.toListSeriesDTO(seriesMongoRepository.findAll());
    }

    @Override
    public SeriesResponseDTO getAllSeriesByOffset(int pageNo, int size) {
        try {
            return SeriesMapper.successListOfSeriesResponse(seriesMongoRepository.findAll(PageRequest.of(pageNo, size)).getContent());
        }catch (IllegalArgumentException illegalArgumentException){
            return SeriesMapper.errorListOfSeriesResponse();
        }
    }

    @Override
    public Optional<SeriesDTO> getSeriesById(String id) {
            return Optional.ofNullable(SeriesMapper.toSeriesDTO(seriesMongoRepository.findById(id).get()));
    }

    @Override
    public SeriesDTO updateSeries(SeriesDTO seriesDTO) {
        Series series = SeriesMapper.toSeries(seriesDTO);
        return SeriesMapper.toSeriesDTO(seriesMongoRepository.save(series));
    }

    @Override
    public void deleteSeriesById(String id) {
        seriesMongoRepository.deleteById(id);
    }

    @Override
    public List<SeriesDTO> getAllSeriesSortedByRating(String sort, int pageNo, int size) {
        if(sort.equals("-rating")) {
            return SeriesMapper.toListSeriesDTO(seriesMongoRepository.findAll(
                    PageRequest.of(pageNo, size, Sort.by(Sort.Direction.DESC, "rating"))).getContent());
        }else if(sort.equals("rating")){
            return SeriesMapper.toListSeriesDTO(seriesMongoRepository.findAll(
                    PageRequest.of(pageNo, size, Sort.by(Sort.Direction.ASC, "rating"))).getContent());
        }else {
            return SeriesMapper.toListSeriesDTO(seriesMongoRepository.findAll(
                    PageRequest.of(pageNo, size)).getContent());
        }
    }

    @Override
    public List<SeriesDTO> getSeriesByRating(Double rating, int pageNo, int size) {
        return SeriesMapper.toListSeriesDTO(seriesMongoRepository.findByRating(rating, PageRequest.of(pageNo, size)).getContent());
    }

    @Override
    public List<SeriesDTO> getSeriesByGenre(String genre, int pageNo, int size) {
        return SeriesMapper.toListSeriesDTO(seriesMongoRepository.findByGenre(genre, PageRequest.of(pageNo, size)).getContent());
    }

    @Override
    public List<SeriesDTO> getSeriesByRatingRange(Double fromRating, Double toRating,int pageNo, int size) {
        return SeriesMapper.toListSeriesDTO(seriesMongoRepository.findByRatingBetween(fromRating, toRating, PageRequest.of(pageNo, size)).getContent());
    }

    @Override
    public List<SeriesDTO> getSeriesByFiltering(Double rating, String genre, int pageNo, int size) {
        List<SeriesDTO> listOfSeries = new ArrayList<>();
        listOfSeries.addAll(getSeriesByRating(rating, pageNo, size));
        listOfSeries.addAll(getSeriesByGenre(genre, pageNo, size));
        return listOfSeries;
    }

    @Override
    public void deleteAllSeries(List<SeriesDTO> listOfSeriesDTO) {
        seriesMongoRepository.deleteAll(SeriesMapper.toListSeries(listOfSeriesDTO));
    }
}
