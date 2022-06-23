package com.gamba.negozio.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.gamba.negozio.entities.Game;
import com.gamba.negozio.entities.Manga;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan("com.gamba.negozio")
public class MangaRestTest {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ObjectWriter objectWriter = objectMapper.writer();

    @Autowired
    WebApplicationContext wac;

    @MockBean
    private NegozioService service;

    Manga MANGA_1 = new Manga(1L,"Naruto", "Hashiro", "Shonen", "Shonen Jump", "4 Ottobre 2000", "Finito", 89, 4.99, "Ninja della Foglia", "www.naruto.com/naruto.jpg", 4.6);
    Manga MANGA_2 = new Manga(2L,"One Piece", "Eichiro Oda", "Shonen", "Shonen Jump", "3 Maggio 1999", "In Corso", 114, 4.99, "All'arrembaggio", "www.one.com/one.jpg", 4.8);
    Manga MANGA_3 = new Manga(3L,"Berserk", "Chisido", "Shonen", "Shonen Jump", "16 Febbraio 2000", "In Corso", 46, 4.99, "Malato", "www.ber.com/ber.jpg", 3.8);

    List<Manga> mangaList =new ArrayList<>(Arrays.asList(MANGA_1,MANGA_2,MANGA_3));

    @BeforeEach
    void setUpMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build();
    }

    @Test
    void getAllManga_success() throws Exception {
        Mockito.when(service.getAllMangas()).thenReturn(mangaList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/manga")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

}
