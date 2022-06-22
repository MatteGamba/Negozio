package com.gamba.negozio.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "game")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String softHouse;
    private String genre;
    private String publisher;
    private String pubDate;
    private double price;
    private String description;
    private String img;
    private double score;

    public Game(long id, String title, String softHouse, String genre, String publisher, String pubDate, double price, String description, String img) {
        this.id = id;
        this.title = title;
        this.softHouse = softHouse;
        this.genre = genre;
        this.publisher = publisher;
        this.pubDate = pubDate;
        this.price = price;
        this.description = description;
        this.img = img;
    }
}
