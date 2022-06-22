package com.gamba.negozio.entities;

import com.gamba.negozio.exception.GameNotFoundException;
import com.gamba.negozio.service.NegozioService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.*;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan("com.gamba.negozio")
public class GameTest {

    @Autowired
    NegozioService service;

    Logger LOGGER = LoggerFactory.getLogger(GameTest.class);

    Game GAME_1 = new Game(2L,"Spider-Man","Insomniac Games","Avventura dinamica","Sony","12 Novembre 2020", 49.99,"Inspired by Marvel","www.game.com/game.jpg");
    Game GAME_2 = new Game(3L,"Death Stranding","Kojima Productions","Avventura dinamica","Sony","8 novembre 2019", 19.99,"A new game by Kojima","www.game.com/game.jpg");
    Game GAME_3 = new Game(4L,"Cyberpunk 2077","CD Projekt RED","Action RPG","CD Projekt","10 dicembre 2020", 35.99,"Punk for game","www.game.com/game.jpg");

    @Before
    public void setUp(){
        service.addGame(GAME_1);
        service.addGame(GAME_2);
        service.addGame(GAME_3);
    }

    @Test
    public void postGameServiceTest(){
        Game game = service.addGame(GAME_1);
        assertNotNull(game);
        assertEquals("Spider-Man", game.getTitle());
        assertEquals("Insomniac Games", game.getSoftHouse());
    }

    @Test
    public void getGameServiceTest(){
        List<Game> games = service.getAllGames();
        assertNotNull(games);
        LOGGER.info(String.valueOf(games));
    }

    @Test
    public void putGameServiceTest(){
        GAME_1.setTitle("Spider-Man Miles Morales");
        GAME_1 = service.updGame(GAME_1);
        assertEquals("Spider-Man Miles Morales", GAME_1.getTitle());
        assertNotEquals("Spider-Man", GAME_1.getTitle());
    }

    @Test
    @Transactional
    public void deleteGameServiceTest(){
        try {

            service.deleteGame(GAME_3.getId());
            service.getGameById(GAME_3.getId());
            Assert.fail();
        }catch (Exception e){
            assertTrue(e instanceof GameNotFoundException);
        }
    }

}
