package org.example.movielibrary.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "favorites",
        uniqueConstraints = @UniqueConstraint(name = "uq_user_movie", columnNames = {"user_id", "movie_id"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "movie_id", nullable = false)
    private Long movieId;
}
