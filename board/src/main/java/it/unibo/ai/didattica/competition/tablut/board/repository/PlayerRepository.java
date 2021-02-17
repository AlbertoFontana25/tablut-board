package it.unibo.ai.didattica.competition.tablut.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unibo.ai.didattica.competition.tablut.board.model.Player;

/**
 * Repository interface for {@link Player}
 * 
 * @author a.fontana
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {

}
