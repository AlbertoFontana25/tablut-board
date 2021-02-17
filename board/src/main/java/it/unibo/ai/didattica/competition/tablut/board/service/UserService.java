package it.unibo.ai.didattica.competition.tablut.board.service;

import java.util.Optional;

import it.unibo.ai.didattica.competition.tablut.board.model.User;

/**
 * Service interface for {@link User}
 * 
 * @author a.fontana
 */
public interface UserService {

	Optional<User> findByUsername(String username);
	
	User save(User user);

}
