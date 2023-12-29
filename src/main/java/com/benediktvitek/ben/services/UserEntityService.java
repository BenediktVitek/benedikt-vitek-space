package com.benediktvitek.ben.services;

import com.benediktvitek.ben.models.UserEntity;
import com.benediktvitek.ben.repositories.RoleRepository;
import com.benediktvitek.ben.repositories.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEntityService {

    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserEntity saveNew(String username, String password) {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.getRoles().add(roleRepository.getRoleByName("USER"));
        return userEntityRepository.save(user);
    }

    public UserEntity save(UserEntity user) {
        return userEntityRepository.save(user);
    }

    public UserEntity getByName(String username) {
        return userEntityRepository.getByUsername(username);
    }
}
