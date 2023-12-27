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
    @Temporal(TemporalType.DATE)
    private Date date;
    @Temporal(TemporalType.TIME)
    private Date time;
    private boolean dummyData;

    public Comment(String message, String name, boolean dummyData) {
        this.date = new Date();
        this.time = new Date();
        this.message = message;
        this.name = name;
        this.dummyData = dummyData;
    }

    public String getRegularDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat dateFormatTime = new SimpleDateFormat("HH:mm");
        return "Posted: " + dateFormat.format(date) + " " + dateFormatTime.format(time);
    }
}