package it.unibo.ai.didattica.competition.tablut.board.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.unibo.ai.didattica.competition.tablut.board.model.User;
import it.unibo.ai.didattica.competition.tablut.board.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Class that loads user data
 * 
 * @author a.fontana
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
	@NonNull
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOpt = userService.findByUsername(username);
		
		if(userOpt.isPresent())
			return userOpt.get();
		
		throw new UsernameNotFoundException("User " + username + " not found");
	}

}
