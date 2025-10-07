package org.example.movielibrary.repo;

import org.example.movielibrary.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    boolean existsByUserIdAndMovieId(Integer userId, Long movieId);
    List<Favorite> findAllByUserId(Integer userId);
    void deleteByUserIdAndMovieId(Integer userId, Long movieId);
}
