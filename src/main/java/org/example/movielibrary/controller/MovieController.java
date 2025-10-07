package org.example.movielibrary.controller;

import org.example.movielibrary.dto.MovieDto;
import org.example.movielibrary.dto.MovieListResponse;
import org.example.movielibrary.service.APIClient;
import org.example.movielibrary.service.FavoriteService;
import org.example.movielibrary.web.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private final APIClient tmdb;
    private final FavoriteService favs;
    private final SessionUser sessionUser;

    @GetMapping({"/", "/library"})
    public String library(@RequestParam(defaultValue = "1") int page, Model model, HttpSession session) {
        Integer userId = sessionUser.getOrCreate(session);

        MovieListResponse resp = tmdb.getLibrary(page);
        model.addAttribute("movies", resp.getResults());
        model.addAttribute("page", resp.getPage());
        model.addAttribute("totalPages", resp.getTotal_pages());

        return "library";
    }

    @GetMapping("/movies/{id}")
    public String movie(@PathVariable long id, Model model, HttpSession session) {
        Integer userId = sessionUser.getOrCreate(session);

        MovieDto movie = tmdb.getMovie(id);
        boolean isFav = favs.isFavorite(userId, id);

        model.addAttribute("movie", movie);
        model.addAttribute("isFav", isFav);
        return "movie";
    }

    @GetMapping("/favorites")
    public String favorites(Model model, HttpSession session) {
        Integer userId = sessionUser.getOrCreate(session);
        model.addAttribute("movies", favs.list(userId));
        return "favorites";
    }

}
