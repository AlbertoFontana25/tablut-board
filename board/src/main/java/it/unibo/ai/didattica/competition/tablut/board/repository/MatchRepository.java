package it.unibo.ai.didattica.competition.tablut.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unibo.ai.didattica.competition.tablut.board.model.Match;

/**
 * Repository interface for {@link Match}
 * 
 * @author a.fontana
 */
public interface MatchRepository extends JpaRepository<Match, Long> {

}
