package org.example.movielibrary.web;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AppErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        var webRequest = new ServletWebRequest(request);
        Map<String, Object> attrs = errorAttributes.getErrorAttributes(
                webRequest,
                ErrorAttributeOptions.defaults()
                        .including(ErrorAttributeOptions.Include.MESSAGE)
        );

        int status = (int) attrs.getOrDefault("status", 500);
        String path = (String) attrs.getOrDefault("path", "");
        String error = (String) attrs.getOrDefault("error", "Unexpected error");
        String message = (String) attrs.getOrDefault("message", "");

        model.addAttribute("status", status);
        model.addAttribute("path", path);
        model.addAttribute("error", error);
        model.addAttribute("message", message);

        if (status == HttpStatus.NOT_FOUND.value()) {
            return "error/404";    // -> src/main/resources/templates/error/404.html
        }
        return "error/error";       // -> src/main/resources/templates/error/error.html (fallback)
    }
}
