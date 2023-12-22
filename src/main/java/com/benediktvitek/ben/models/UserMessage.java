package com.benediktvitek.ben.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "user_message")
public class UserMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;
    private String message;
    private String name;
    private Date date;

    public UserMessage(String message, String name) {
        this.date = new Date();
        this.message = message;
        this.name = name;
    }
}