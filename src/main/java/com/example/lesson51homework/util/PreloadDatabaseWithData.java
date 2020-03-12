package com.example.lesson51homework.util;

import com.example.lesson51homework.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

@Configuration
public class PreloadDatabaseWithData {
    @Bean
    CommandLineRunner initAlbumRepository(AlbumRepository repository) {
        repository.deleteAll();

        return (args) -> Stream.of(albums())
                .peek(System.out::println)
                .forEach(repository::save);
    }

    private Album[] albums() {

        Singer queen = new Singer("Queen");

        Album opera = new Album("A Night at the Opera", LocalDate.parse("1975", DateTimeFormatterUtil.DTF));
        opera.addTrack(new Track("Death on Two Legs (Dedicated to…)", "03:43"));
        opera.addTrack(new Track("Lazing on a Sunday Afternoon", "01:07"));
        opera.addTrack(new Track("I’m in Love with My Car", "03:05"));
        opera.addTrack(new Track("You’re My Best Friend", "02:52"));
        opera.addTrack(new Track("’39", "03:31"));
        opera.addTrack(new Track("Sweet Lady", "04:03"));
        opera.addTrack(new Track("Seaside Rendezvous", "02:15"));

        opera.addSinger(queen);

        Album news = new Album("News of the World", LocalDate.parse("1977", DateTimeFormatterUtil.DTF));
        news.addTrack(new Track("We Will Rock You", "02:01"));
        news.addTrack(new Track("We Are the Champions", "02:59"));
        news.addTrack(new Track("Sheer Heart Attack", "03:24"));
        news.addTrack(new Track("All Dead, All Dead", "03:09"));
        news.addTrack(new Track("Spread Your Wings", "04:32"));
        news.addTrack(new Track("Fight From The Inside", "03:03"));

        news.addSinger(queen);

        Singer acdc = new Singer("AC/DC");

        Album black = new Album("Back in Black", LocalDate.parse("1980", DateTimeFormatterUtil.DTF));
        black.addTrack(new Track("Hells Bells", "05:12"));
        black.addTrack(new Track("Shoot to Thrill", "05:17"));
        black.addTrack(new Track("What Do You Do for Money Honey", "03:35"));
        black.addTrack(new Track("Givin’ the Dog a Bone", "03:31"));
        black.addTrack(new Track("Let Me Put My Love into You", "04:15"));
        black.addTrack(new Track("Back in Black", "04:15"));
        black.addTrack(new Track("You Shook Me All Night Long", "03:30"));
        black.addTrack(new Track("Have a Drink on Me", "03:58"));
        black.addTrack(new Track("Shake a Leg", "04:05"));
        black.addTrack(new Track("Rock And Roll Ain’t Noise Pollution", "04:16"));

        black.addSinger(acdc);

        Singer dp = new Singer("Deep Purple");

        Album rock = new Album("Deep Purple in Rock", LocalDate.parse("1970", DateTimeFormatterUtil.DTF));
        rock.addTrack(new Track("Speed King", "05:49"));
        rock.addTrack(new Track("Bloodsucker", "04:10"));
        rock.addTrack(new Track("Child in Time", "10:14"));

        rock.addSinger(dp);

        Album machine = new Album("Machine Head", LocalDate.parse("1972", DateTimeFormatterUtil.DTF));
        machine.addTrack(new Track("Smoke on the Water", "05:40"));
        machine.addTrack(new Track("Lazy", "07:19"));
        machine.addTrack(new Track("Space Truckin'", "04:31"));

        machine.addSinger(dp);

        return new Album[]{opera, news, black, rock, machine};
    }

    @Bean
    CommandLineRunner initUserRepository(UserRepository repository) {
        repository.deleteAll();

        return (args) -> Stream.of(users())
                .peek(System.out::println)
                .forEach(repository::save);
    }

    private User[] users() {
        User user = new User("user@example.com", "123");
        user.addSearch(new Search("123", "singer", LocalDateTime.now()));
        return new User[]{user};
    }
}
