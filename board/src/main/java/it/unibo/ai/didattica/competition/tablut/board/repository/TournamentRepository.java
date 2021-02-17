package it.unibo.ai.didattica.competition.tablut.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.unibo.ai.didattica.competition.tablut.board.model.Tournament;

/**
 * Repository interface for {@link Tournament}
 * 
 * @author a.fontana
 */
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
	
	@Query( "select t from Tournament t left join fetch t.teams where t.idTournament = :idTournament" )
	Optional<Tournament> findByIdFullDetail(Long idTournament);

}
