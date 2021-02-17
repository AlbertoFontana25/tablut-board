package it.unibo.ai.didattica.competition.tablut.board.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unibo.ai.didattica.competition.tablut.board.repository.MatchRepository;
import it.unibo.ai.didattica.competition.tablut.board.service.MatchService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {
	
	@NonNull
	private MatchRepository matchRepository;

}
