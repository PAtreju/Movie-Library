package org.example.movielibrary.controller;

import org.example.movielibrary.dto.MovieDto;
import org.example.movielibrary.dto.MovieListResponse;
import org.example.movielibrary.service.APIClient;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.*;


@Controller
@RequiredArgsConstructor
public class MovieController {

    private final APIClient tmdb;

    @GetMapping({"/", "/library"})
    public String library(@RequestParam(defaultValue = "1") int page, Model model, HttpSession session) {
        MovieListResponse resp = tmdb.getLibrary(page);
        model.addAttribute("movies", resp.getResults());
        model.addAttribute("page", resp.getPage());
        model.addAttribute("totalPages", resp.getTotal_pages());
        return "library";
    }

}