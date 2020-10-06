package com.pankaj.SeriesInfoService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SeriesDTO implements Serializable{

    private static final long serialVersionUID = 7482375968933319642L;

    private String id;
    private String name;
    private String genre;
    private Double rating;
    private String description;
    private int numberOfSeason;

}
