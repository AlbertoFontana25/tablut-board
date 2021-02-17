package it.unibo.ai.didattica.competition.tablut.board.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unibo.ai.didattica.competition.tablut.board.model.Team;
import it.unibo.ai.didattica.competition.tablut.board.repository.TeamRepository;
import it.unibo.ai.didattica.competition.tablut.board.service.TeamService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

	@NonNull
	private TeamRepository teamRepository;
	
	@Override
	public Optional<Team> findByIdFullDetail(Long idTournament, Long idTeam) {
		return teamRepository.findByIdFullDetail(idTournament, idTeam);
	}

	@Override
	public List<Team> findAll() {
		return teamRepository.findAll();
	}

	@Override
	public Page<Team> findAll(int page, int size) {
		return teamRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public Team save(Team team) {
		return teamRepository.save(team);
	}

}
