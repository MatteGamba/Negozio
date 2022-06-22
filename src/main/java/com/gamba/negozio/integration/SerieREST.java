package com.gamba.negozio.integration;

import com.gamba.negozio.entities.Game;
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

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Serie> deleteSerie(@PathVariable("id") Long id){
        service.deleteSerie(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
