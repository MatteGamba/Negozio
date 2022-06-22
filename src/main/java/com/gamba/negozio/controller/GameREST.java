package com.gamba.negozio.controller;

import com.gamba.negozio.entities.Game;
import com.gamba.negozio.service.NegozioService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("api/game")
public class GameREST {

    private NegozioService service;

    private Logger LOGGER = LoggerFactory.getLogger(GameREST.class);

    @Autowired
    public GameREST(NegozioService service) {
        super();
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<Game>> getAllGames(){
        List<Game> games = service.getAllGames();
        return new ResponseEntity<List<Game>>(games, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable("id") Long id){
        Game oneGame = service.getGameById(id);
        return new ResponseEntity<Game>(oneGame, HttpStatus.OK);
    }

    @GetMapping("/name/{title}")
    public ResponseEntity<List<Game>> getGameByTitle(@PathVariable("title") String title){
        List<Game> titleGame = service.findGameByTitle(title);
        return new ResponseEntity<List<Game>>(titleGame, HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<Game> addGame(@RequestBody Game game){
        Game newGame = service.addGame(game);
        return new ResponseEntity<Game>(newGame, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Game> updateGame(@RequestBody Game game){
        Game updGame = service.updGame(game);
        return new ResponseEntity<Game>(updGame, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Game> deleteGame(@PathVariable("id") Long id){
        service.deleteGame(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/price")
    public ResponseEntity<List<Game>> getGameByPrice(){
        List<Game> gamePrice = service.orderGameByPrice();
        return new ResponseEntity<List<Game>>(gamePrice, HttpStatus.OK);
    }

    @GetMapping("/score")
    public ResponseEntity<List<Game>> getGameByScore(){
        List<Game> gameScore = service.orderGameByScore();
        return new ResponseEntity<List<Game>>(gameScore, HttpStatus.OK);
    }





}
