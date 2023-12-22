package com.benediktvitek.ben.repositories;

import com.benediktvitek.ben.models.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserMessageRepository extends JpaRepository<UserMessage, UUID> {
}