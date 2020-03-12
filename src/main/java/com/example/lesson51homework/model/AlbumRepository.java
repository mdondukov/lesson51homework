package com.example.lesson51homework.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public interface AlbumRepository extends MongoRepository<Album, String> {
    @Query("{ 'singers.name': {$regex:?0, $options: 'i'} }")
    Stream<Album> findAlbumsBySingersNameLike(String name);

    @Query("{ 'tracks.title': {$regex:?0, $options: 'i'} }")
    Stream<Album> findAlbumsByTracksTitleLike(String title);

    List<Album> findByYear(LocalDate year);

    List<Album> findByTitle(String title);
}
