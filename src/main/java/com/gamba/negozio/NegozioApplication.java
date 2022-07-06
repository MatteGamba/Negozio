package com.gamba.negozio;

import com.gamba.negozio.entities.Game;
import com.gamba.negozio.entities.Manga;
import com.gamba.negozio.entities.Serie;
import com.gamba.negozio.repo.GameRepo;
import com.gamba.negozio.repo.MangaRepo;
import com.gamba.negozio.repo.SerieRepo;
import com.gamba.negozio.service.NegozioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
public class NegozioApplication implements CommandLineRunner {

	@Autowired
	GameRepo gameRepo;
	@Autowired
	SerieRepo serieRepo;
	@Autowired
	MangaRepo mangaRepo;
	@Autowired
	NegozioService service;

	public static void main(String[] args) {
		SpringApplication.run(NegozioApplication.class, args);
	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}


	@Override
	public void run(String... args) throws Exception {

		Game G1 = new Game(1L,"God of War", "Santa Monica", "Avventura", "Sony", "24 Ottobre 2020", 49.99, "Great Game", "www.god.com/god.jpg", 4.8);
		Game G2 = new Game(2L,"Cyberpunk 2077", "CD Project", "Punk", "Cd Project RED", "12 Novembre 2021", 37.99, "Punk Game", "www.punk.com/punk.jpg", 3.5);
		Game G3 = new Game(3L,"Spider-Man", "Marvel", "Fantasy", "Sony", "5 Gennaio 2019", 29.99, "Fly with him", "www.spid.com/spid.jpg", 4.2);

		gameRepo.save(G1);
		gameRepo.save(G2);
		gameRepo.save(G3);

		Serie S1 = new Serie(1L, "Peaky Blinders", "Netflix", "Mafia", "Thommy, Arthur","18 Febbraio 2018", "In Corso", 19.99, "Calligham", "Fantastic Serie", "www.peaky.com/peaky.jpg", 4.8);
		Serie S2 = new Serie(2L, "Better Call Saul", "Netflix", "Criminal", "Saul, Kim","7 Settembre 2019", "In Corso", 15.49, "Vince", "Meglio Chiamare Saul", "www.saul.com/saul.jpg", 4.5);
		Serie S3 = new Serie(3L, "Obi-Wan-Kenobi", "Disney", "Fantasy", "Obi, Darth","4 Maggio 2022", "In Corso", 17.99, "Lucas Film", "Luke sono tuo padre", "www.obi.com/obi.jpg", 3.9);

		serieRepo.save(S1);
		serieRepo.save(S2);
		serieRepo.save(S3);

		Manga M1 = new Manga(1L,"Naruto", "Hashiro", "Shonen", "Shonen Jump", "4 Ottobre 2000", "Finito", 89, 4.99, "Ninja della Foglia", "www.naruto.com/naruto.jpg", 4.6);
		Manga M2 = new Manga(2L,"One Piece", "Eichiro Oda", "Shonen", "Shonen Jump", "3 Maggio 1999", "In Corso", 114, 4.99, "All'arrembaggio", "www.one.com/one.jpg", 4.8);
		Manga M3 = new Manga(3L,"Berserk", "Chisido", "Shonen", "Shonen Jump", "16 Febbraio 2000", "In Corso", 46, 4.99, "Malato", "www.ber.com/ber.jpg", 3.8);

		mangaRepo.save(M1);
		mangaRepo.save(M2);
		mangaRepo.save(M3);

	}
}
