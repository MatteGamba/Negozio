package com.gamba.negozio.repo;

import com.gamba.negozio.entities.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SerieRepo extends JpaRepository<Serie, Long> {

    void deleteSerieById(Long id);
    Optional<Serie> findSerieById(Long id);

}
