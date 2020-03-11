package com.example.lesson51homework.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "albums")
@Data
public class Album {
    @Id
    private String albumId;
    private String title;
    private LocalDate year;
    private List<Track> tracks;

    public Album(String title, LocalDate year) {
        this.title = title;
        this.year = year;
    }

    public void addTrack(Track track) {
        if (tracks == null) tracks = new ArrayList<>();
        tracks.add(track);
    }

    @Override
    public String toString() {
        return title + ", " + year;
    }
}
