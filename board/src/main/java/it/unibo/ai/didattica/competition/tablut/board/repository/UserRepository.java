package it.unibo.ai.didattica.competition.tablut.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import it.unibo.ai.didattica.competition.tablut.board.model.User;

/**
 * Repository interface for {@link User}
 * 
 * @author a.fontana
 */
public interface UserRepository extends JpaRepository<User, Long> {

	@EntityGraph( value = "user-with-roles" )
	Optional<User> findByUsername(String username);

}
