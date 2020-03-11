package com.example.lesson51homework;

import com.example.lesson51homework.model.SingerRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Lesson51homeworkApplication.class)
public class AppTest {
    private static final Logger LOG = LoggerFactory.getLogger(AppTest.class);

    @Autowired
    private SingerRepository singerRepository;

    @Test
    public void testCountOfSingers() {
        LOG.info("********* Number of Singers TEST *********");
        LOG.info("Singers count: " + singerRepository.count());
        LOG.info("*******************************************");
    }
}
