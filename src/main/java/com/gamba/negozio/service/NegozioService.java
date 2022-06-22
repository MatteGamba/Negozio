package com.gamba.negozio.service;

import com.gamba.negozio.entities.Game;
import com.gamba.negozio.entities.Manga;
import com.gamba.negozio.entities.Serie;

import java.util.List;

public interface NegozioService {

    List<Game> getAllGames();
    List<Serie> getAllSeries();
    List<Manga> getAllMangas();

    Game getGameById(long id);
    Serie getSerieById(long id);
    Manga getMangaById(long id);

    Game addGame(Game game);
    Serie addSerie(Serie serie);
    Manga addManga(Manga manga);

    Game updGame(Game game);
    Serie updSerie(Serie serie);
    Manga updManga(Manga manga);

    void deleteGame(long id);
    void deleteSerie(long id);
    void deleteManga(long id);

    List<Game> findGameByTitle(String title);
    List<Serie> findSerieByTitle(String title);
    List<Manga> findMangaByTitle(String title);

    List<Game> orderGameByPrice();
    List<Serie> orderSerieByPrice();
    List<Manga> orderMangaByPrice();

    List<Game> orderGameByScore();
    List<Serie> orderSerieByScore();
    List<Manga> orderMangaByScore();

    void updateScoreGame(long id, double score);
    void updateScoreManga(long id, double score);
    void updateScoreSerie(long id, double score);

}
