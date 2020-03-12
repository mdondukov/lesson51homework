package com.example.lesson51homework;

import com.example.lesson51homework.model.*;
import com.example.lesson51homework.util.DateTimeFormatterUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@SpringBootTest
class Lesson51homeworkApplicationTests {
    private static final Logger LOG = LoggerFactory.getLogger(Lesson51homeworkApplicationTests.class);

    @Autowired
    private AlbumRepository repository;

    @Test
    void contextLoads() {
    }

    @Test
    public void testCountOfAlbums() {
        LOG.info("*** Number of Albums ***");
        LOG.info("Albums count: " + repository.count());
        LOG.info("************************");
    }

    @Test
    public void testCountOfSingers() {
        LOG.info("*** Number of Singers ***");

        List<Album> albums = repository.findAll();
        long countAllSingers = albums.stream()
                .map(Album::getSingers)
                .flatMap(Collection::stream)
                .distinct()
                .count();

        LOG.info("Singers count: " + countAllSingers);
        LOG.info("*************************");
    }

    @Test
    public void testFindAllAlbums() {
        LOG.info("*** All Albums ***");

        Iterable<Album> albums = repository.findAll();
        for (Album album : albums) {
            LOG.info("Album: " + album);
        }

        LOG.info("******************");
    }

    @Test
    public void testFindAlbumsTitleLike() {
        LOG.info("*** All Albums with Specific Word ***");
        String query = "A Night at the Opera";

        List<Album> albums = repository.findByTitle(query);
        for (Album album : albums) {
            LOG.info("Album: " + album);
        }

        LOG.info("*************************************");
    }

    @Test
    public void testFindAlbumsByYear() {
        LOG.info("*** All Albums by 1970 ***");

        repository.findByYear(LocalDate.parse("1970", DateTimeFormatterUtil.DTF))
        .forEach(album -> LOG.info("Album: " + album));

        LOG.info("**************************");
    }

    @Test
    public void testFindAllSingers() {
        LOG.info("*** All Singers ***");

        List<Album> albums = repository.findAll();
        List<Singer> singers = albums.stream()
                .map(Album::getSingers)
                .flatMap(Collection::stream)
                .distinct()
                .collect(toList());

        for (Singer singer : singers) {
            LOG.info("Singer: " + singer);
        }

        LOG.info("*******************");
    }

    @Test
    public void testFindSingersTitleLike() {
        LOG.info("*** All Singers with Specific Word ***");
        String query = ".*deep.*";

        List<Singer> singers = repository.findAlbumsBySingersNameLike(query)
                .map(Album::getSingers)
                .flatMap(Collection::stream)
                .filter(s -> s.getName().toLowerCase().matches(query.toLowerCase()))
                .sorted(Comparator.comparing(Singer::getName))
                .distinct()
                .collect(toList());

        for (Singer singer : singers) {
            LOG.info("Singer: " + singer);
        }

        LOG.info("**************************************");
    }

    @Test
    public void testFindAlbumsBySinger() {
        LOG.info("*** All Albums of Queen ***");

        Stream<Album> albums = repository.findAlbumsBySingersNameLike(".*queen.*");
        albums.forEach(album -> LOG.info("Album: " + album));

        LOG.info("***************************");
    }

    @Test
    public void testFindTracksTitleLike() {
        LOG.info("*** All Tracks with Specific Word ***");
        String query = ".*all.*";

        List<Track> tracks = repository.findAlbumsByTracksTitleLike(query)
                .map(Album::getTracks)
                .flatMap(Collection::stream)
                .filter(track -> track.getTitle().toLowerCase().matches(query.toLowerCase()))
                .sorted(Comparator.comparing(Track::getTitle))
                .collect(toList());

        for (Track track : tracks) {
            LOG.info("Track: " + track);
        }

        LOG.info("*************************************");
    }
}
