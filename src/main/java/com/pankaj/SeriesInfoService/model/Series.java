package com.pankaj.SeriesInfoService.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Series implements Serializable {

	private static final long serialVersionUID = 7482375968933319643L;

	@Id
	private String id;
	private String name;
	private String genre;
	private Double rating;
	private String description;
	private int numberOfSeason;

}
