package it.unibo.ai.didattica.competition.tablut.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import it.unibo.ai.didattica.competition.tablut.board.model.Player;

/**
 * Service interface for {@link Player}
 * 
 * @author a.fontana
 */
public interface PlayerService {
	
	Optional<Player> findById(Long idPlayer);

	List<Player> findAll();

	Page<Player> findAll(int page, int size);

	Player save(Player player);

}
