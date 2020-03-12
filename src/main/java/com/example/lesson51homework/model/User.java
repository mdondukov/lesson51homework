package com.example.lesson51homework.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
public class User {
    @Id
    private String userId;
    private String email;
    private String password;
    private List<Search> searches;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void addSearch(Search search) {
        if (searches == null) searches = new ArrayList<>();
        searches.add(search);
    }
}
