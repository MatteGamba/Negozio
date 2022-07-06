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
@RequestMapping("api/manga")
public class MangaREST {

    private NegozioService service;

    private Logger LOGGER = LoggerFactory.getLogger(MangaREST.class);

    @Autowired
    public MangaREST(NegozioService service) {
        super();
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<Manga>> getAllManga(){
        List<Manga> mangas = service.getAllMangas();
        return new ResponseEntity<List<Manga>>(mangas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manga> getMangaById(@PathVariable("id") Long id){
        Manga oneManga = service.getMangaById(id);
        return new ResponseEntity<Manga>(oneManga, HttpStatus.OK);
    }

    @GetMapping("/name/{title}")
    public ResponseEntity<List<Manga>> getMangaByTitle(@PathVariable("title") String title){
        List<Manga> titleManga = service.findMangaByTitle(title);
        return new ResponseEntity<List<Manga>>(titleManga, HttpStatus.OK);
    }

    @GetMapping("/total")
    public int getTotalManga(){
        return service.getTotalManga();
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<Manga> addManga(@RequestBody Manga manga){
        Manga newManga = service.addManga(manga);
        return new ResponseEntity<Manga>(newManga, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Manga> updateManga(@RequestBody Manga manga){
        Manga updManga = service.updManga(manga);
        return new ResponseEntity<Manga>(updManga, HttpStatus.OK);
    }

    @PutMapping("/update/{id}/{score}")
    public ResponseEntity<Manga> updateScoreManga(@PathVariable("id") long id, @PathVariable("score") double score){
        service.updateScoreManga(id, score);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Serie> deleteSerie(@PathVariable("id") Long id){
        service.deleteSerie(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/price")
    public ResponseEntity<List<Manga>> getMangaByPrice(){
        List<Manga> mangaPrice = service.orderMangaByPrice();
        return new ResponseEntity<List<Manga>>(mangaPrice, HttpStatus.OK);
    }

    @GetMapping("/score")
    public ResponseEntity<List<Manga>> getMangaByScore(){
        List<Manga> mangaScore = service.orderMangaByScore();
        return new ResponseEntity<List<Manga>>(mangaScore, HttpStatus.OK);
    }

}
