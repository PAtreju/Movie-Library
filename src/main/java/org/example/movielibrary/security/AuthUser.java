package org.example.movielibrary.security;

import lombok.RequiredArgsConstructor;
import org.example.movielibrary.entity.User;
import org.example.movielibrary.repo.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthUser {

    private final UserRepository users;

    public Integer requireUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            throw new IllegalStateException("Not authenticated");
        }
        String username = auth.getName();
        return users.findByUsername(username).map(User::getId)
                .orElseThrow(() -> new IllegalStateException("User not found: " + username));
    }

    public boolean isAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal());
    }

    public String usernameOrNull() {
        return isAuthenticated() ? SecurityContextHolder.getContext().getAuthentication().getName() : null;
    }
}
