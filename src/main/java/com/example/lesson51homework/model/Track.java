package com.example.lesson51homework.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@Document(collection = "tracks")
@Data
public class Track {
    @Id
    private String trackId;
    private String title;
    private String duration;

    public Track(String title, String duration) {
        this.title = title;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return title + ", " + duration;
    }
}
