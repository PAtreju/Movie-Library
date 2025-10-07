package org.example.movielibrary.web;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.movielibrary.service.ExampleUser;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionUser {

    public static final String KEY = "CURRENT_USER_ID";
    private final ExampleUser demoUserService;

    public Integer getOrCreate(HttpSession session) {
        Object val = session.getAttribute(KEY);
        if (val instanceof Integer id) return id;
        Integer id = demoUserService.ensureExampleUser();
        session.setAttribute(KEY, id);
        return id;
    }
}
