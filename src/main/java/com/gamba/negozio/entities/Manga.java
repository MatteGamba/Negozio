package com.gamba.negozio.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "manga")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Manga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String mangaka;
    private String genre;
    private String publisher;
    private String pubDate;
    private String stato;
    private int volumi;
    private double price;
    private String description;
    private String img;
    private double score;

    public Manga(long id, String title, String mangaka, String genre, String publisher, String pubDate, String stato, int volumi, double price, String description, String img) {
        this.id = id;
        this.title = title;
        this.mangaka = mangaka;
        this.genre = genre;
        this.publisher = publisher;
        this.pubDate = pubDate;
        this.stato = stato;
        this.volumi = volumi;
        this.price = price;
        this.description = description;
        this.img = img;
    }
}
