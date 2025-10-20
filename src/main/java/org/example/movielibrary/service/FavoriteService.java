package org.example.movielibrary.service;

import lombok.RequiredArgsConstructor;
import org.example.movielibrary.dto.MovieDto;
import org.example.movielibrary.entity.Favorite;
import org.example.movielibrary.repo.FavoriteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favorites;
    private final APIClientService tmdb;

    public boolean isFavorite(Integer userId, long movieId) {
        return favorites.existsByUserIdAndMovieId(userId, movieId);
    }

    @Transactional
    public void add(Integer userId, long movieId) {
        if (!favorites.existsByUserIdAndMovieId(userId, movieId)) {
            favorites.save(Favorite.builder()
                    .userId(userId)
                    .movieId(movieId)
                    .build());
        }
    }

    @Transactional
    public void remove(Integer userId, long movieId) {
        favorites.deleteByUserIdAndMovieId(userId, movieId);
    }

    public List<MovieDto> list(Integer userId) {
        return favorites.findAllByUserId(userId)
                .stream()
                .map(f -> tmdb.getMovie(f.getMovieId()))
                .toList();
    }
}
