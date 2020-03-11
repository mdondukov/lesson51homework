package com.example.lesson51homework.util;

import com.example.lesson51homework.model.Album;
import com.example.lesson51homework.model.Singer;
import com.example.lesson51homework.model.SingerRepository;
import com.example.lesson51homework.model.Track;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.stream.Stream;

@Configuration
public class PreloadDatabaseWithData {
    private static final Logger LOG = LoggerFactory.getLogger(PreloadDatabaseWithData.class);
    private static final DateTimeFormatter DTF = new DateTimeFormatterBuilder()
            .appendPattern("yyyy")
            .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
            .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 1)
            .toFormatter();

    @Bean
    CommandLineRunner initDatabase(SingerRepository singerRepository) {
        singerRepository.deleteAll();

        return (args) -> Stream.of(singers())
                .peek(System.out::println)
                .forEach(singerRepository::save);
    }

    private Singer[] singers() {
        Singer queen = new Singer("Queen");

        Album opera = new Album("A Night at the Opera", LocalDate.parse("1975", DTF));
        opera.addTrack(new Track("Death on Two Legs (Dedicated to…)", "03:43"));
        opera.addTrack(new Track("Lazing on a Sunday Afternoon", "01:07"));
        opera.addTrack(new Track("I’m in Love with My Car", "03:05"));
        opera.addTrack(new Track("You’re My Best Friend", "02:52"));
        opera.addTrack(new Track("’39", "03:31"));
        opera.addTrack(new Track("Sweet Lady", "04:03"));
        opera.addTrack(new Track("Seaside Rendezvous", "02:15"));
        queen.addAlbum(opera);

        Album news = new Album("News of the World", LocalDate.parse("1977", DTF));
        news.addTrack(new Track("We Will Rock You", "02:01"));
        news.addTrack(new Track("We Are the Champions", "02:59"));
        news.addTrack(new Track("Sheer Heart Attack", "03:24"));
        news.addTrack(new Track("All Dead, All Dead", "03:09"));
        news.addTrack(new Track("Spread Your Wings", "04:32"));
        news.addTrack(new Track("Fight From The Inside", "03:03"));
        queen.addAlbum(news);

        return new Singer[]{queen};
    }
}
