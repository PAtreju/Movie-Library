package org.example.movielibrary.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.movielibrary.entity.User;
import org.example.movielibrary.repo.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExampleUser {

    private final UserRepository users;

    @Transactional
    public Integer ensureExampleUser() {
        return users.findByUsername("admin")
                .map(User::getId)
                .orElseGet(() -> {
                    User u = User.builder()
                            .username("admin")
                            .password("123") // placeholder; no security wired yet
                            .email("admin@example.com")
                            .build();
                    return users.save(u).getId();
                });
    }
}
