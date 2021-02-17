package it.unibo.ai.didattica.competition.tablut.board.restcontroller;

import java.time.Instant;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import it.unibo.ai.didattica.competition.tablut.board.component.GameHandler;
import it.unibo.ai.didattica.competition.tablut.board.model.Player;
import it.unibo.ai.didattica.competition.tablut.board.model.StartOperationResult;
import it.unibo.ai.didattica.competition.tablut.board.model.StartOperationResult.StartResult;
import it.unibo.ai.didattica.competition.tablut.board.service.PlayerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Rest api to start server and players
 * 
 * @author a.fontana
 */
@RestController
@RequestMapping( "/api/game" )
@RequiredArgsConstructor
@Slf4j
public class GameRestController {

	@NonNull
	private GameHandler gameHandler;
	
	@NonNull
	private PlayerService playerService;
	
	@GetMapping( "/start-server" )
	public StartOperationResult startServer() {
		StartOperationResult startOperationResult = new StartOperationResult();
		try {
			gameHandler.startServer();
			
			startOperationResult.setStartDate(Instant.now());
			startOperationResult.setResult(StartResult.STARTED);
		} catch (Exception e) {
			log.error("cannot start the server", e);
			
			startOperationResult.setResult(StartResult.ERROR);
		}
		
		return startOperationResult;
	}

	@GetMapping( "/start-player" )
	public StartOperationResult startPlayer(@RequestParam Long idPlayer) {
		Optional<Player> playerOpt = playerService.findById(idPlayer);
		
		if(!playerOpt.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player with id: " + idPlayer + " not found");
		}
		
		Player player = playerOpt.get();
		
		StartOperationResult startOperationResult = new StartOperationResult();
		try {
			gameHandler.startPlayer(player.getName(), player.getShellScript(), null);
			
			startOperationResult.setStartDate(Instant.now());
			startOperationResult.setResult(StartResult.STARTED);
		} catch (Exception e) {
			log.error("cannot start the player " + player, e);
			
			startOperationResult.setResult(StartResult.ERROR);
		}
		
		return startOperationResult;
		
	}
	
}