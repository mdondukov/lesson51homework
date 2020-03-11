package com.example.lesson51homework.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Document(collection = "albums")
@Data
public class Album {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");

    @Id
    private String albumId;
    private String title;
    private LocalDate year;
}
