package com.example.lesson51homework;

import com.example.lesson51homework.model.Album;
import com.example.lesson51homework.model.Singer;
import com.example.lesson51homework.model.SingerRepository;
import com.example.lesson51homework.model.Track;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@SpringBootTest
class Lesson51homeworkApplicationTests {

    private static final Logger LOG = LoggerFactory.getLogger(Lesson51homeworkApplicationTests.class);

    @Autowired
    private SingerRepository singerRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void testCountOfSingers() {
        LOG.info("*** Number of Singers ***");
        LOG.info("Singers count: " + singerRepository.count());
        LOG.info("*************************");
    }

    @Test
    public void testFindAll() {
        LOG.info("*** All Singers ***");

        Iterable<Singer>  allSingers = singerRepository.findAll();
        for (Singer singer : allSingers) {
            LOG.info("Singer: " + singer);
        }

        LOG.info("*******************");
    }

    @Test
    public void testFindByName() {
        LOG.info("*** All Albums of Queen ***");

        Singer singer = singerRepository.findByName("Queen");
        List<Album> albums = singer.getAlbums();
        for (Album album : albums) {
            LOG.info("Album: " + album);
        }

        LOG.info("***************************");
    }

    @Test
    public void testFindAllTracksBySingerName() {
        LOG.info("*** All Tracks of Queen ***");

        Singer singer = singerRepository.findByName("Queen");
        List<Track> tracks = singer.getAlbums().stream()
                .map(Album::getTracks)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Track::getTitle))
                .collect(toList());

        for (Track track : tracks) {
            LOG.info("Track: " + track);
        }

        LOG.info("***************************");
    }
}
