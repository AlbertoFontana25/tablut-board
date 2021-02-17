package it.unibo.ai.didattica.competition.tablut.board.component.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import it.unibo.ai.didattica.competition.tablut.board.component.GameHandler;
import it.unibo.ai.didattica.competition.tablut.board.configuration.GameServerConfiguration;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of {@link GameHandler} with simple shell operations
 * 
 * @author a.fontana
 */
@Component
@RequiredArgsConstructor
public class GameHandlerImpl implements GameHandler {

	@NonNull
	private GameServerConfiguration gameServerConfiguration;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startServer() {
		Thread gameServer = new Thread(new GameServerStartThread(gameServerConfiguration), "S-Thread");
		gameServer.start();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startPlayer(String playerName, String command, List<String> args) {
		Thread player = new Thread(new PlayerStartThread(playerName, gameServerConfiguration.getBasePath() + command, args), "P-" + playerName + "");
		player.start();
	}
	
	/**
	 * Thread that start the game server
	 * 
	 * @author a.fontana
	 */
	@RequiredArgsConstructor
	@Slf4j
	protected static class GameServerStartThread implements Runnable {
		
		@NonNull
		private GameServerConfiguration gameServerConfiguration;
		
		@Override
		public void run() {
			ProcessBuilder processBuilder = new ProcessBuilder(gameServerConfiguration.getArgs());
			processBuilder.command(gameServerConfiguration.getBasePath() + gameServerConfiguration.getServerScript());
			try {
				processBuilder.start();
				log.info("Game server started successfully");
			} catch (IOException e) {
				log.error("error on the start of the server", e);
			}
		}
	}
	
	/**
	 * Thread that start a generic player
	 * 
	 * @author a.fontana
	 */
	@RequiredArgsConstructor
	@AllArgsConstructor
	@Slf4j
	protected static class PlayerStartThread implements Runnable {
		
		@NonNull
		private String playerName;
		
		@NonNull
		private String command;
		
		
		private List<String> args;
		
		@Override
		public void run() {
			ProcessBuilder processBuilder = (args != null) ? new ProcessBuilder(args) : new ProcessBuilder();
			processBuilder.command(command);
			try {
				processBuilder.start();
				log.info("Player " + playerName + " started successfully");
			} catch (IOException e) {
				log.error("error on the start of the player " + playerName, e);
			}
		}
		
	}
	
}
