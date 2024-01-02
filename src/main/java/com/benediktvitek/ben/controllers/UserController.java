package com.benediktvitek.ben.controllers;

import com.benediktvitek.ben.repositories.UserEntityRepository;
import com.benediktvitek.ben.services.UserEntityService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserEntityRepository userEntityRepository;
    private final UserEntityService userEntityService;
    private final HttpSession httpSession;


    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        RedirectAttributes redirectAttributes) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    SecurityContextHolder.getContext());
            redirectAttributes.addFlashAttribute("loginSuccess", "Logged in successfully!");
            return "redirect:/index";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("loginFailed", "Wrong username or password");
            return "redirect:/index";
        }
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           RedirectAttributes redirectAttributes) {

        if (userEntityRepository.existsByUsername(username)) {
            redirectAttributes.addFlashAttribute("registerFailed", "This user already exists");
            return "redirect:/index";
        }
        userEntityService.saveNew(username, password);
        redirectAttributes.addFlashAttribute("registrationSuccess", "Registration successful!");

        return "redirect:/index";

    }

}
