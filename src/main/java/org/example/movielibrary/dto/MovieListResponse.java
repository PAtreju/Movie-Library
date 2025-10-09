package org.example.movielibrary.dto;

import lombok.Data;

import java.util.List;

@Data
public class MovieListResponse {

    private int page;
    private List<MovieDto> results;
    private int total_pages;
}