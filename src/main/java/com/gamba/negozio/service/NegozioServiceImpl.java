package com.gamba.negozio.service;

import com.gamba.negozio.entities.Game;
import com.gamba.negozio.entities.Manga;
import com.gamba.negozio.entities.Serie;
import com.gamba.negozio.exception.GameNotFoundException;
import com.gamba.negozio.exception.MangaNotFoundException;
import com.gamba.negozio.exception.SerieNotFoundException;
import com.gamba.negozio.repo.GameRepo;
import com.gamba.negozio.repo.MangaRepo;
import com.gamba.negozio.repo.SerieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NegozioServiceImpl implements NegozioService{

    private GameRepo gameRepo;
    private SerieRepo serieRepo;
    private MangaRepo mangaRepo;

    @Autowired
    public NegozioServiceImpl(GameRepo gameRepo, SerieRepo serieRepo, MangaRepo mangaRepo) {
        this.gameRepo = gameRepo;
        this.serieRepo = serieRepo;
        this.mangaRepo = mangaRepo;
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepo.findAll();
    }

    @Override
    public List<Serie> getAllSeries() {
        return serieRepo.findAll();
    }

    @Override
    public List<Manga> getAllMangas() {
        return mangaRepo.findAll();
    }

    @Override
    public Game getGameById(long id) {
        return gameRepo.findGameById(id)
                .orElseThrow(()-> new GameNotFoundException(String.valueOf(id)));
    }

    @Override
    public Serie getSerieById(long id) {
        return serieRepo.findSerieById(id)
                .orElseThrow(()-> new MangaNotFoundException(String.valueOf(id)));
    }

    @Override
    public Manga getMangaById(long id) {
        return mangaRepo.findMangaById(id)
                .orElseThrow(()-> new SerieNotFoundException(String.valueOf(id)));
    }

    @Override
    public Game addGame(Game game) {
        return gameRepo.save(game);
    }

    @Override
    public Serie addSerie(Serie serie) {
        return serieRepo.save(serie);
    }

    @Override
    public Manga addManga(Manga manga) {
        return mangaRepo.save(manga);
    }

    @Override
    public Game updGame(Game game) {
        return gameRepo.save(game);
    }

    @Override
    public Serie updSerie(Serie serie) {
        return serieRepo.save(serie);
    }

    @Override
    public Manga updManga(Manga manga) {
        return mangaRepo.save(manga);
    }

    @Override
    public void deleteGame(long id) {
        gameRepo.deleteGameById(id);
    }

    @Override
    public void deleteSerie(long id) {
        serieRepo.deleteSerieById(id);
    }

    @Override
    public void deleteManga(long id) {
        mangaRepo.deleteMangaById(id);
    }

    @Override
    public List<Game> findGameByTitle(String title) {
        List<Game> games = gameRepo.findAll();
        List<Game> gameWithName = new ArrayList<>();
        games.forEach(game -> {
            if(game.getTitle().toLowerCase().contains(title.toLowerCase())){
                gameWithName.add(game);
            };
        });

        return gameWithName;
    }

    @Override
    public List<Serie> findSerieByTitle(String title) {
        List<Serie> series = serieRepo.findAll();
        List<Serie> serieWithName = new ArrayList<>();
        series.forEach(serie -> {
            if(serie.getTitle().toLowerCase().contains(title.toLowerCase())){
                serieWithName.add(serie);
            };
        });

        return serieWithName;
    }

    @Override
    public List<Manga> findMangaByTitle(String title) {
        List<Manga> mangas = mangaRepo.findAll();
        List<Manga> mangaWithName = new ArrayList<>();
        mangas.forEach(manga -> {
            if(manga.getTitle().toLowerCase().contains(title.toLowerCase())){
                mangaWithName.add(manga);
            };
        });

        return mangaWithName;
    }

    @Override
    public List<Game> orderGameByPrice() {
        List<Game> games = gameRepo.findAll();
        return games.stream()
                .sorted(Comparator.comparing(Game::getPrice).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Serie> orderSerieByPrice() {
        List<Serie> series = serieRepo.findAll();
        return series.stream()
                .sorted(Comparator.comparing(Serie::getPrice).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Manga> orderMangaByPrice() {
        List<Manga> mangas = mangaRepo.findAll();
        return mangas.stream()
                .sorted(Comparator.comparing(Manga::getPrice).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Game> orderGameByScore() {
        List<Game> games = gameRepo.findAll();
        return games.stream().sorted(Comparator.comparing(Game::getScore).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Serie> orderSerieByScore() {
        List<Serie> series = serieRepo.findAll();
        return series.stream().sorted(Comparator.comparing(Serie::getScore).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Manga> orderMangaByScore() {
        List<Manga> mangas = mangaRepo.findAll();
        return mangas.stream().sorted(Comparator.comparing(Manga::getScore).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public void updateScoreGame(long id, double score) {
        Game game = gameRepo.findGameById(id)
                .orElseThrow(()-> new GameNotFoundException(String.valueOf(id)));
        game.setScore(score);
        gameRepo.save(game);
    }

    @Override
    public void updateScoreManga(long id, double score) {
        Manga manga = mangaRepo.findMangaById(id)
                .orElseThrow(()-> new MangaNotFoundException(String.valueOf(id)));
        manga.setScore(score);
        mangaRepo.save(manga);
    }

    @Override
    public void updateScoreSerie(long id, double score) {
        Serie serie = serieRepo.findSerieById(id)
                .orElseThrow(()-> new SerieNotFoundException(String.valueOf(id)));
        serie.setScore(score);
        serieRepo.save(serie);
    }

}
