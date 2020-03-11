package com.example.lesson51homework;

import com.example.lesson51homework.model.Singer;
import com.example.lesson51homework.model.SingerRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        LOG.info("*** Number of Singers TEST ***");
        LOG.info("Singers count: " + singerRepository.count());
        LOG.info("******************************");
    }

    @Test
    public void testFindAll() {
        LOG.info("*** ALL SINGERS ***");

        Iterable<Singer>  allSingers = singerRepository.findAll();
        for (Singer singer : allSingers) {
            LOG.info(singer.toString());
        }

        LOG.info("*******************");
    }
}
