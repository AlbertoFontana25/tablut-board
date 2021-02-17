package it.unibo.ai.didattica.competition.tablut.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.unibo.ai.didattica.competition.tablut.board.model.Team;

/**
 * Repository interface for {@link Team}
 * 
 * @author a.fontana
 */
public interface TeamRepository extends JpaRepository<Team, Long> {
	
	@Query( "select t from Team t left join fetch t.players where t.tournament.idTournament = :idTournament and t.idTeam = :idTeam" )
	Optional<Team> findByIdFullDetail(Long idTournament, Long idTeam);

}

