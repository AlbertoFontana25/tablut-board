package it.unibo.ai.didattica.competition.tablut.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import it.unibo.ai.didattica.competition.tablut.board.model.Team;

/**
 * Service interface for {@link Team}
 * 
 * @author a.fontana
 */
public interface TeamService {
	
	Optional<Team> findByIdFullDetail(Long idTournament, Long idTeam);

	List<Team> findAll();

	Page<Team> findAll(int page, int size);

	Team save(Team team);

}
