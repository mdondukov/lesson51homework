package com.example.lesson51homework.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "singers")
@Data
public class Singer {
    @Id
    private String singerId;
    private String name;
}
