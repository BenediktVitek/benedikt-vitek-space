package com.benediktvitek.ben.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(length = 140)
    private String message;
    @Column(length = 20)
    private String name;
    private Date date;
    private boolean dummyData;

    public Comment(String message, String name, boolean dummyData) {
        this.date = new Date();
        this.message = message;
        this.name = name;
        this.dummyData = dummyData;
    }

    public String getRegularDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd. MM. yyyy HH:mm");
        return "Posted" + dateFormat.format(date);
    }
}