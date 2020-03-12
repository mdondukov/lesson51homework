package com.example.lesson51homework.model;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
@Deprecated
public interface SingerRepository extends CrudRepository<Singer, String> {
    Singer findByName(String name);

    @Query("{'albums.tracks.title':{$regex:?0, $options: 'i'}}")
    List<Singer> findSingersWithSpecificTracks(String partOfTrackTitle);
}
