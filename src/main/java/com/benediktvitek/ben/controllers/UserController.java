package com.benediktvitek.ben.controllers;

import com.benediktvitek.ben.dtos.errors.ErrorDTO;
import com.benediktvitek.ben.dtos.requests.LoginDTO;
import com.benediktvitek.ben.dtos.requests.RegisterDTO;
import com.benediktvitek.ben.dtos.responses.SuccessDTO;
import com.benediktvitek.ben.models.UserEntity;
import com.benediktvitek.ben.repositories.RoleRepository;
import com.benediktvitek.ben.repositories.UserEntityRepository;
import com.benediktvitek.ben.services.UserEntityService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserEntityRepository userEntityRepository;
    private final UserEntityService userEntityService;
    private final HttpSession httpSession;


    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    SecurityContextHolder.getContext());
            return "redirect:/index";
        } catch (Exception e) {
            return "redirect:/index";
        }
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {

        if (userEntityRepository.existsByUsername(username)) {
            return "redirect:/index";
        }
        userEntityService.saveNew(username, password);


        return "redirect:/index";

    }

}
