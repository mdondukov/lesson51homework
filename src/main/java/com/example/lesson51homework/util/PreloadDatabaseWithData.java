package com.example.lesson51homework.util;

import com.example.lesson51homework.model.Album;
import com.example.lesson51homework.model.Singer;
import com.example.lesson51homework.model.SingerRepository;
import com.example.lesson51homework.model.Track;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

@Configuration
public class PreloadDatabaseWithData {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");

    @Bean
    void initData(SingerRepository singerRepository) {
        singerRepository.deleteAll();

        Singer queen = new Singer("Queen");
        Album opera = new Album("A Night at the Opera", LocalDate.parse("1975", dtf));
        opera.addTrack(new Track("Death on Two Legs (Dedicated to…)", LocalTime.parse("03:43")));
        opera.addTrack(new Track("Lazing on a Sunday Afternoon", LocalTime.parse("01:07")));
        opera.addTrack(new Track("I’m in Love with My Car", LocalTime.parse("03:05")));
        opera.addTrack(new Track("You’re My Best Friend", LocalTime.parse("02:52")));
        opera.addTrack(new Track("’39", LocalTime.parse("03:31")));
        opera.addTrack(new Track("Sweet Lady", LocalTime.parse("04:03")));
        opera.addTrack(new Track("Seaside Rendezvous", LocalTime.parse("02:15")));
        queen.addAlbum(opera);

        Album news = new Album("News of the World", LocalDate.parse("1977", dtf));
        news.addTrack(new Track("We Will Rock You", LocalTime.parse("02:01")));
        news.addTrack(new Track("We Are the Champions", LocalTime.parse("02:59")));
        news.addTrack(new Track("Sheer Heart Attack", LocalTime.parse("03:24")));
        news.addTrack(new Track("All Dead, All Dead", LocalTime.parse("03:09")));
        news.addTrack(new Track("Spread Your Wings", LocalTime.parse("04:32")));
        news.addTrack(new Track("Fight From The Inside", LocalTime.parse("03:03")));
        queen.addAlbum(news);

        singerRepository.saveAll(Collections.singletonList(queen));
    }
}
