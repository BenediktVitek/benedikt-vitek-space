package com.benediktvitek.ben.repositories;

import com.benediktvitek.ben.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role getRoleByName(String name);
}