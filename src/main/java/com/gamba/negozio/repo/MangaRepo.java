package com.gamba.negozio.repo;

import com.gamba.negozio.entities.Manga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MangaRepo extends JpaRepository<Manga, Long> {

    void deleteMangaById(Long id);
    Optional<Manga> findMangaById(Long id);


}
