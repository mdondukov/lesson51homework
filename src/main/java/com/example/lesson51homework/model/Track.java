package com.example.lesson51homework.model;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tracks")
@Data
public class Track {
    @Indexed
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
