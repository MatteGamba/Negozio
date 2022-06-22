package com.gamba.negozio.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.gamba.negozio.entities.Game;
import com.gamba.negozio.service.NegozioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan("com.gamba.negozio")
class GameRestTest {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ObjectWriter objectWriter = objectMapper.writer();
    @Autowired
    WebApplicationContext wac;

    @MockBean
    private NegozioService service;

    Game GAME_1 = new Game(2L,"Spider-Man","Insomniac Games","Avventura dinamica","Sony","12 Novembre 2020", 49.99,"Inspired by Marvel","www.game.com/game.jpg");
    Game GAME_2 = new Game(3L,"Death Stranding","Kojima Productions","Avventura dinamica","Sony","8 novembre 2019", 19.99,"A new game by Kojima","www.game.com/game.jpg");
    Game GAME_3 = new Game(4L,"Cyberpunk 2077","CD Projekt RED","Action RPG","CD Projekt","10 dicembre 2020", 35.99,"Punk for game","www.game.com/game.jpg");
    List<Game> gameList =new ArrayList<>(Arrays.asList(GAME_1,GAME_2,GAME_3));


    @BeforeEach
    void setUpMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build();
    }

    @Test
    void getAllGames_success() throws Exception {
        Mockito.when(service.getAllGames()).thenReturn(gameList);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/game")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void getGameById_success() throws Exception {
        Mockito.when(service.getGameById(GAME_1.getId())).thenReturn(GAME_1);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/game/" + GAME_1.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    void getGameByTitle() {

    }

    @Test
    void addGame_succes() throws Exception {
        Game game = Game.builder()
                .id(5L)
                .description("Grande Film")
                .genre("Action")
                .img("www.game.com/game.jpg")
                .price(20.20)
                .pubDate("18 Agosto 2017")
                .publisher("Sony")
                .softHouse("Insomniac")
                .title("Cyberpunk 2020")
                .build();

        Mockito.when(service.addGame(game)).thenReturn(game);
        String content = objectWriter.writeValueAsString(game);

        MockHttpServletRequestBuilder mockRequest =MockMvcRequestBuilders.post("/api/game/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(content().json(content));
    }

    @Test
    void updateGame_success() throws Exception {
        Game updateGame = Game.builder()
                .id(4L)
                .description("No Punk for Game")
                .genre("Quiet RPG")
                .img("www.gamenew.com/game.jpg")
                .price(135.99)
                .pubDate("10 dicembre 2077")
                .publisher("DVD Projekt")
                .softHouse("CD Projekt BLUE")
                .title("Matteo'Story")
                .build();

        Mockito.when(service.updGame(updateGame)).thenReturn(updateGame);
        String updateContent = objectWriter.writeValueAsString(updateGame);
        MockHttpServletRequestBuilder mockRequest =MockMvcRequestBuilders.put("/api/game/update")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(updateContent);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(content().json(updateContent));
    }

    @Test
    void deleteGame_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/game/delete/"+GAME_3.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}