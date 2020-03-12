package com.example.lesson51homework;

import com.example.lesson51homework.model.*;
import com.example.lesson51homework.util.DateTimeFormatterUtil;
import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@SpringBootTest
class Lesson51homeworkApplicationTests {
    private static final Logger LOG = LoggerFactory.getLogger(Lesson51homeworkApplicationTests.class);

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void testCountOfAlbums() {
        LOG.info("*** Number of Albums ***");
        LOG.info("Albums count: " + albumRepository.count());
        LOG.info("************************");
    }

    @Test
    public void testCountOfSingers() {
        LOG.info("*** Number of Singers ***");

        List<Album> albums = albumRepository.findAll();
        long countAllSingers = albums.stream()
                .map(Album::getSingers)
                .flatMap(Collection::stream)
                .distinct()
                .count();

        LOG.info("Singers count: " + countAllSingers);
        LOG.info("*************************");
    }

    @Test
    public void testCountOfTracks() {
        LOG.info("*** Number of Tracks ***");

        List<Album> albums = albumRepository.findAll();
        long countAllTracks = albums.stream()
                .map(Album::getTracks)
                .flatMap(Collection::stream)
                .distinct()
                .count();

        LOG.info("Tracks count: " + countAllTracks);
        LOG.info("************************");
    }

    @Test
    public void testFindAllAlbums() {
        LOG.info("*** All Albums ***");

        Iterable<Album> albums = albumRepository.findAll();
        for (Album album : albums) {
            LOG.info("Album: " + album);
        }

        LOG.info("******************");
    }

    @Test
    public void testFindAlbumsTitleLike() {
        LOG.info("*** All Albums with Specific Word ***");
        String query = "A Night at the Opera";

        List<Album> albums = albumRepository.findByTitle(query);
        for (Album album : albums) {
            LOG.info("Album: " + album);
        }

        LOG.info("*************************************");

        saveSearchQuery(query, Album.class.getSimpleName().toLowerCase());
    }

    @Test
    public void testFindAlbumsByYear() {
        LOG.info("*** All Albums by 1970 ***");
        String query = "1970";

        albumRepository.findByYear(LocalDate.parse(query, DateTimeFormatterUtil.DTF))
        .forEach(album -> LOG.info("Album: " + album));

        LOG.info("**************************");

        saveSearchQuery(query, Album.class.getSimpleName().toLowerCase());
    }

    @Test
    public void testFindAllSingers() {
        LOG.info("*** All Singers ***");

        List<Album> albums = albumRepository.findAll();
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

        List<Singer> singers = albumRepository.findAlbumsBySingersNameLike(query)
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

        saveSearchQuery(query, Singer.class.getSimpleName().toLowerCase());
    }

    @Test
    public void testFindAlbumsBySinger() {
        LOG.info("*** All Albums of Queen ***");
        String query = ".*queen.*";

        Stream<Album> albums = albumRepository.findAlbumsBySingersNameLike(query);
        albums.forEach(album -> LOG.info("Album: " + album));

        LOG.info("***************************");

        saveSearchQuery(query, Album.class.getSimpleName().toLowerCase());
    }

    @Test
    public void testFindTracksTitleLike() {
        LOG.info("*** All Tracks with Specific Word ***");
        String query = ".*all.*";

        List<Track> tracks = albumRepository.findAlbumsByTracksTitleLike(query)
                .map(Album::getTracks)
                .flatMap(Collection::stream)
                .filter(track -> track.getTitle().toLowerCase().matches(query.toLowerCase()))
                .sorted(Comparator.comparing(Track::getTitle))
                .collect(toList());

        for (Track track : tracks) {
            LOG.info("Track: " + track);
        }

        LOG.info("*************************************");

        saveSearchQuery(query, Track.class.getSimpleName().toLowerCase());
    }

    @Test
    public void testSaveSearchQueryInSpecificUser() {
        LOG.info("*** Save Search ***");
        String query = "Queen";

        User user = userRepository.findByEmail("user@example.com");
        user.addSearch(new Search(query, "album", LocalDateTime.now()));
        user.getSearches().forEach(search -> LOG.info(search.toString()));

        LOG.info("*******************");
    }

    public void saveSearchQuery(String query, String type) {
        LOG.info(" ");
        LOG.info("*** User Search Saved! ***");

        User user = userRepository.findByEmail("user@example.com");
        user.addSearch(new Search(query, type, LocalDateTime.now()));
        user.getSearches().forEach(search -> LOG.info(search.toString()));

        LOG.info("**************************");
    }

    @After
    @Test
    public void testFindAllUserSearches() {
        LOG.info("*** All Searches by Specific User ***");

        User user = userRepository.findByEmail("user@example.com");
        user.getSearches().forEach(search -> LOG.info(search.toString()));

        LOG.info("*************************************");
    }
}
