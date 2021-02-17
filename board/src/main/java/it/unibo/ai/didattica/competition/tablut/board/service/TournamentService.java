package it.unibo.ai.didattica.competition.tablut.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import it.unibo.ai.didattica.competition.tablut.board.model.Tournament;

/**
 * Service interface for {@link Tournament}
 * 
 * @author a.fontana
 */
public interface TournamentService {
	
	Optional<Tournament> findById(Long idTournament);

	Optional<Tournament> findByIdFullDetail(Long idTournament);

	List<Tournament> findAll();

	Page<Tournament> findAll(int page, int size);

	Tournament save(Tournament team);

}
