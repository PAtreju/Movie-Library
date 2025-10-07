package org.example.movielibrary.service;

import org.example.movielibrary.dto.MovieListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Service
@RequiredArgsConstructor
public class APIClient {


    private final RestTemplate restTemplate;


    @Value("${tmdb.baseUrl:https://api.themoviedb.org/3}")
    private String baseUrl;


    @Value("${tmdb.apiKey}")
    private String apiKey;


    public MovieListResponse getLibrary(int page) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/movie/popular")
                .queryParam("api_key", apiKey)
                .queryParam("language", "en-US")
                .queryParam("page", page)
                .toUriString();


        return restTemplate.getForObject(url, MovieListResponse.class);
    }
}