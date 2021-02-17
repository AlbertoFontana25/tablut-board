package it.unibo.ai.didattica.competition.tablut.board.model;

import lombok.Data;

/**
 * State of a game. We have a representation of the board and the turn.
 * 
 * Documentation from Tablut2019 project
 * 
 * @author a.fontana
 */
@Data
public class State {
	
	/**
	 * Represents the board as a matrix of {@link Pawn}
	 */
	public Pawn[][] board;
	
	/**
	 * Represents the {@link Turn}
	 */
	public Turn turn;

	/**
	 * Turn represent the player that has to move or the end of the game(A win
	 * by a player or a draw)
	 * 
	 * Documentation from Tablut2019 project
	 * 
	 * @author a.fontana
	 */
	public static enum Turn {
		WHITE,
		BLACK,
		WHITEWIN,
		BLACKWIN,
		DRAW;
	}
	
	/**
	 * Pawn represents the content of a box in the board
	 * 
	 * Documentation from Tablut2019 project
	 * 
	 * @author a.fontana
	 */
	public static enum Pawn {
		EMPTY,
		WHITE,
		BLACK,
		THRONE,
		KING;
	}
}
