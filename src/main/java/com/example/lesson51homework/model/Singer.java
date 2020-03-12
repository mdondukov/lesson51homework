package com.example.lesson51homework.model;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "singers")
@Data
public class Singer {
    @Indexed
    private String name;

    public Singer(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
