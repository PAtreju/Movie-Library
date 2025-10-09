package org.example.movielibrary.controller;

import lombok.RequiredArgsConstructor;
import org.example.movielibrary.dto.MovieDto;
import org.example.movielibrary.dto.MovieListResponse;
import org.example.movielibrary.security.AuthUser;
import org.example.movielibrary.service.APIClient;
import org.example.movielibrary.service.FavoriteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private final APIClient tmdb;
    private final FavoriteService favs;
    private final AuthUser auth;

    @GetMapping({"/", "/library"})
    public String library(@RequestParam(defaultValue = "1") int page, Model model) {
        MovieListResponse resp = tmdb.getLibrary(page);
        model.addAttribute("movies", resp.getResults());
        model.addAttribute("page", resp.getPage());
        model.addAttribute("totalPages", resp.getTotal_pages());
        model.addAttribute("username", auth.usernameOrNull());
        return "library";
    }

    @GetMapping("/movies/{id}")
    public String movie(@PathVariable long id, Model model) {
        MovieDto movie = tmdb.getMovie(id);
        boolean isFav = auth.isAuthenticated() && favs.isFavorite(auth.requireUserId(), id);
        model.addAttribute("movie", movie);
        model.addAttribute("isFav", isFav);
        model.addAttribute("username", auth.usernameOrNull());
        return "movie";
    }

    @PostMapping("/favorites/{id}")
    public String addFavorite(@PathVariable long id,
                              @RequestParam(required = false) String from) {
        // will throw if not authenticated; ensure /favorites/** is protected in SecurityConfig
        favs.add(auth.requireUserId(), id);
        return redirectBack(from, id);
    }

    @PostMapping("/favorites/{id}/remove")
    public String removeFavorite(@PathVariable long id,
                                 @RequestParam(required = false) String from) {
        favs.remove(auth.requireUserId(), id);
        return redirectBack(from, id);
    }

    @GetMapping("/favorites")
    public String favorites(Model model) {
        model.addAttribute("movies", favs.list(auth.requireUserId()));
        model.addAttribute("username", auth.usernameOrNull());
        return "favorites";
    }

    @GetMapping("/search")
    public String search(@RequestParam String query,
                         @RequestParam(defaultValue = "1") int page,
                         Model model) {
        MovieListResponse resp = tmdb.searchMovies(query, page);
        model.addAttribute("query", query);
        model.addAttribute("movies", resp.getResults());
        model.addAttribute("page", resp.getPage());
        model.addAttribute("totalPages", resp.getTotal_pages());
        model.addAttribute("username", auth.usernameOrNull());
        return "search";
    }

    private String redirectBack(String from, long id) {
        if ("movie".equals(from)) return "redirect:/movies/" + id;
        if ("favorites".equals(from)) return "redirect:/favorites";
        return "redirect:/library";
    }
}
