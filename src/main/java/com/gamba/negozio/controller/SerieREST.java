package com.gamba.negozio.controller;

import com.gamba.negozio.entities.Game;
import com.gamba.negozio.entities.Manga;
import com.gamba.negozio.entities.Serie;
import com.gamba.negozio.service.NegozioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("api/serie")
public class SerieREST {

    private NegozioService service;

    private Logger LOGGER = LoggerFactory.getLogger(SerieREST.class);

    @Autowired
    public SerieREST(NegozioService service) {
        super();
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<Serie>> getAllSeries(){
        List<Serie> series = service.getAllSeries();
        return new ResponseEntity<List<Serie>>(series, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Serie> getSerieById(@PathVariable("id") Long id){
        Serie oneSerie = service.getSerieById(id);
        return new ResponseEntity<Serie>(oneSerie, HttpStatus.OK);
    }

    @GetMapping("/name/{title}")
    public ResponseEntity<List<Serie>> getSerieByTitle(@PathVariable("title") String title){
        List<Serie> titleSerie = service.findSerieByTitle(title);
        return new ResponseEntity<List<Serie>>(titleSerie, HttpStatus.OK);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Serie>> getSerieByGenre(@PathVariable("genre") String genre){
        List<Serie> genreSerie = service.findSerieByGenre(genre);
        return new ResponseEntity<List<Serie>>(genreSerie, HttpStatus.OK);
    }

    @GetMapping("/total")
    public int getTotalSerie(){
        return service.getTotalSerie();
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<Serie> addSerie(@RequestBody Serie serie){
        Serie newSerie = service.addSerie(serie);
        return new ResponseEntity<Serie>(newSerie, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Serie> updateSerie(@RequestBody Serie serie){
        Serie updSerie = service.updSerie(serie);
        return new ResponseEntity<Serie>(updSerie, HttpStatus.OK);
    }

    @PutMapping("/update/{id}/{score}")
    public ResponseEntity<Serie> updateScoreSerie(@PathVariable("id") long id, @PathVariable("score") double score){
        service.updateScoreSerie(id, score);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Serie> deleteSerie(@PathVariable("id") Long id){
        service.deleteSerie(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/price")
    public ResponseEntity<List<Serie>> getMangaByPrice(){
        List<Serie> seriePrice = service.orderSerieByPrice();
        return new ResponseEntity<List<Serie>>(seriePrice, HttpStatus.OK);
    }

    @GetMapping("/score")
    public ResponseEntity<List<Serie>> getMangaByScore(){
        List<Serie> serieScore = service.orderSerieByScore();
        return new ResponseEntity<List<Serie>>(serieScore, HttpStatus.OK);
    }

}
