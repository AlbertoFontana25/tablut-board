package it.unibo.ai.didattica.competition.tablut.board.model;

import java.util.UUID;

import lombok.Data;

/**
 * Represent a wrapper for the game state that store all the informations of a
 * match for tablut board web application
 * 
 * @author a.fontana
 */
@Data
public class StateWrapper {

	/**
	 * UUID for the match
	 */
	private UUID uuid;

	/**
	 * {@link State} of the match
	 */
	private State state;

	/**
	 * White player name
	 */
	private String whitePlayerName;

	/**
	 * Black player name
	 */
	private String blackPlayerName;

	/**
	 * Last {@link Action}
	 */
	private Action lastAction;

	/**
	 * {@link Result} of the match
	 */
	private Result result;

	/**
	 * Represent the result of a match
	 * 
	 * @author a.fontana
	 *
	 */
	public enum Result {
		START,
		PLAYING,
		BLACKWIN, 
		WHITEWIN, 
		BLACKERROR, 
		WHITEERROR, 
		BLACKTIMEOUT, 
		WHITETIMEOUT, 
		DRAW, 
		ERROR;
	}

}
