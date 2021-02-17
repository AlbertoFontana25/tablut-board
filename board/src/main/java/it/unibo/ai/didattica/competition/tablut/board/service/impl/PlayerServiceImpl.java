package it.unibo.ai.didattica.competition.tablut.board.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unibo.ai.didattica.competition.tablut.board.model.Player;
import it.unibo.ai.didattica.competition.tablut.board.repository.PlayerRepository;
import it.unibo.ai.didattica.competition.tablut.board.service.PlayerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

	@NonNull
	private PlayerRepository playerRepository;
	
	@Override
	public Optional<Player> findById(Long idPlayer) {
		return playerRepository.findById(idPlayer);
	}
	
	@Override
	public List<Player> findAll() {
		return playerRepository.findAll();
	}

	@Override
	public Page<Player> findAll(int page, int size) {
		return playerRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public Player save(Player player) {
		return playerRepository.save(player);
	}

}
