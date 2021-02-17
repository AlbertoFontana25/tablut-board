package it.unibo.ai.didattica.competition.tablut.board.component;

import java.util.List;

/**
 * Represents the operation to start the server and players
 * 
 * @author a.fontana
 */
public interface GameHandler {

	/**
	 * Start a {@link GameServerStartThread} using the {@link GameServerConfiguration}
	 */
	void startServer();

	/**
	 * Start a player using the parameter
	 * 
	 * @param playerName
	 * @param command
	 * @param args
	 */
	void startPlayer(String playerName, String command, List<String> args);

}
