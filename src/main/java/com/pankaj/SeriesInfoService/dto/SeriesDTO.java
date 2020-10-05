package com.pankaj.SeriesInfoService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeriesDTO {

    private String id;
    private String name;
    private String genre;
    private Double rating;
    private String description;
    private int numberOfSeason;

}
