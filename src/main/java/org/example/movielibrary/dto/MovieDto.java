package org.example.movielibrary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class MovieDto {
    private long id;
    @JsonProperty("poster_path")
    private String posterPath;
}
