package com.benediktvitek.ben.controllers;

import com.benediktvitek.ben.dtos.errors.ErrorDTO;
import com.benediktvitek.ben.dtos.requests.LoginDTO;
import com.benediktvitek.ben.dtos.requests.RegisterDTO;
import com.benediktvitek.ben.dtos.responses.SuccessDTO;
import com.benediktvitek.ben.models.UserEntity;
import com.benediktvitek.ben.repositories.RoleRepository;
import com.benediktvitek.ben.repositories.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RestUserController {

    private final AuthenticationManager authenticationManager;
    private final UserEntityRepository userEntityRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok().body(new SuccessDTO("Login successful"));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(new ErrorDTO("Bad username or password"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDto) {

        if (userEntityRepository.existsByUsername(registerDto.username())) {
            return ResponseEntity.badRequest().body(new ErrorDTO("Username already exists!"));
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.username());
        user.setPassword(passwordEncoder.encode(registerDto.password()));
        user.getRoles().add(roleRepository.getRoleByName("USER"));
        userEntityRepository.save(user);

        return ResponseEntity.ok().body(new SuccessDTO("New user registered"));

    }

}
