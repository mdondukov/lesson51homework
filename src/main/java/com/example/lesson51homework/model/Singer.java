package com.example.lesson51homework.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "singers")
@Data
public class Singer {
    @Id
    private String singerId;
    private String name;
    private List<Album> albums;

    public Singer(String name) {
        this.name = name;
    }

    public void addAlbum(Album album) {
        if (albums == null) albums = new ArrayList<>();
        albums.add(album);
    }

    @Override
    public String toString() {
        return "Singer{" +
                "name='" + name + '\'' +
                '}';
    }
}
