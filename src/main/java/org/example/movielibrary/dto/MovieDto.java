package org.example.movielibrary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MovieDto {

    private long id;
    private String title;
    @JsonProperty("poster_path")
    private String posterPath;
    @JsonProperty("backdrop_path")
    private String backdropPath;
    @JsonProperty("release_date")
    private String releaseDate;
    private String overview;
    @JsonProperty("vote_average")
    private double voteAverage;
}