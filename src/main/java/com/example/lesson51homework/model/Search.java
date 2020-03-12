package com.example.lesson51homework.model;

import lombok.Data;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "searches")
@Data
@CompoundIndex(def = "{'query':1, 'when':1}")
public class Search {
    private String query;
    private String type;
    private LocalDateTime when;

    public Search(String query, String type, LocalDateTime when) {
        this.query = query;
        this.type = type;
        this.when = when;
    }

    @Override
    public String toString() {
        return "Search: " +
                "query='" + query + '\'' +
                ", type=" + type +
                ", when=" + when;
    }
}
