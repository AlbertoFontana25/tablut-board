package it.unibo.ai.didattica.competition.tablut.board.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unibo.ai.didattica.competition.tablut.board.model.User;
import it.unibo.ai.didattica.competition.tablut.board.repository.UserRepository;
import it.unibo.ai.didattica.competition.tablut.board.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	@NonNull
	private UserRepository userRepository;
	
	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

}
