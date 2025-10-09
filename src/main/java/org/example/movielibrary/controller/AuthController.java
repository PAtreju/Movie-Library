package org.example.movielibrary.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.movielibrary.entity.User;
import org.example.movielibrary.repo.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository users;
    private final BCryptPasswordEncoder encoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("form", new SignupForm());
        return "signup";
    }

    @PostMapping("/signup")
    public String doSignup(@ModelAttribute("form") SignupForm form, BindingResult br, Model model) {
        if (users.findByUsername(form.getUsername()).isPresent()) {
            br.rejectValue("username", "exists", "Username already taken");
        }
        if (br.hasErrors()) {
            return "signup";
        }
        User u = User.builder()
                .username(form.getUsername())
                .email(form.getEmail())
                .password(encoder.encode(form.getPassword()))
                .build();
        users.save(u);
        return "redirect:/login?registered";
    }

    @Data
    public static class SignupForm {

        @NotBlank
        @Size(min = 3, max = 50)
        private String username;
        @NotBlank
        @Email
        private String email;
        @NotBlank
        @Size(min = 6, max = 100)
        private String password;
    }
}
