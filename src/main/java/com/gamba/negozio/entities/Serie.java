package com.gamba.negozio.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "serie")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String production;
    private String genre;
    private String actors;
    private String pubDate;
    private String stato;
    private double price;
    private String writer;
    private String description;
    private String img;
    private double score;

    public Serie(long id, String title, String production, String genre, String actors, String pubDate, String stato, double price, String writer, String description, String img) {
        this.id = id;
        this.title = title;
        this.production = production;
        this.genre = genre;
        this.actors = actors;
        this.pubDate = pubDate;
        this.stato = stato;
        this.price = price;
        this.writer = writer;
        this.description = description;
        this.img = img;
    }
}
