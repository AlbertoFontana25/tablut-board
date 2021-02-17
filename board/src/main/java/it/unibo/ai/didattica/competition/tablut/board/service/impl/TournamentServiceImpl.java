package it.unibo.ai.didattica.competition.tablut.board.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unibo.ai.didattica.competition.tablut.board.model.Tournament;
import it.unibo.ai.didattica.competition.tablut.board.repository.TournamentRepository;
import it.unibo.ai.didattica.competition.tablut.board.service.TournamentService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TournamentServiceImpl implements TournamentService {
	
	@NonNull
	private TournamentRepository tournamentRepository;

	@Override
	public Optional<Tournament> findById(Long idTournament) {
		return tournamentRepository.findById(idTournament);
	}
	
	@Override
	public Optional<Tournament> findByIdFullDetail(Long idTournament) {
		return tournamentRepository.findByIdFullDetail(idTournament);
	}

	@Override
	public List<Tournament> findAll() {
		return tournamentRepository.findAll();
	}

	@Override
	public Page<Tournament> findAll(int page, int size) {
		return tournamentRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public Tournament save(Tournament team) {
		return tournamentRepository.save(team);
	}

}
