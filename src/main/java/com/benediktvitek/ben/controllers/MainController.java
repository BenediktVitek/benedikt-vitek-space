package com.benediktvitek.ben.controllers;

import com.benediktvitek.ben.dtos.responses.MovieDTO;
import com.benediktvitek.ben.services.CommentService;
import com.benediktvitek.ben.services.MovieService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MovieService movieService;
    private final CommentService commentService;

    @GetMapping({"/", "/index", ""})
    public String index(Model model, HttpServletRequest request) {

        List<MovieDTO> moviesWithRatings = null;
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        try {
            moviesWithRatings = movieService.getTopTenMoviesDto();
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        if (inputFlashMap != null) {
            if (inputFlashMap.get("registerFailed") != null) {
                model.addAttribute("registerFailed", inputFlashMap.get("registerMessage"));
            } else if (inputFlashMap.get("loginFailed") != null) {
                model.addAttribute("loginFailed", inputFlashMap.get("loginFailed"));
            } else if (inputFlashMap.get("loginSuccess") != null) {
                model.addAttribute("loginSuccess", inputFlashMap.get("loginSuccess"));
            } else if (inputFlashMap.get("registrationSuccess") != null) {
                model.addAttribute("registrationSuccess", inputFlashMap.get("registrationSuccess"));
            } else if (inputFlashMap.get("commentSuccess") != null) {
                model.addAttribute("commentSuccess", inputFlashMap.get("commentSuccess"));
            }
        }
        model.addAttribute("movieList", moviesWithRatings);

        return "index";
    }

    @PostMapping("/add-comment")
    public String addComment(String comment,
                             RedirectAttributes redirectAttributes) {

        try {
            commentService.saveMessage(comment);
        } catch (BadRequestException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        redirectAttributes.addFlashAttribute("commentSuccess", "Comment added! Check it here -->");
        return "redirect:/index";
    }


}
