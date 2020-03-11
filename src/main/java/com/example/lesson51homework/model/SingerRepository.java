package com.example.lesson51homework.model;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SingerRepository extends CrudRepository<Singer, String> {
    Singer findByName(String name);
}
