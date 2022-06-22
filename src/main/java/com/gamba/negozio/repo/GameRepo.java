package com.gamba.negozio.repo;

import com.gamba.negozio.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepo extends JpaRepository<Game, Long> {

    void deleteGameById(Long id);
    Optional<Game> findGameById(Long id);

}
