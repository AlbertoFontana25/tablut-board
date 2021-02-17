package it.unibo.ai.didattica.competition.tablut.board.model;

import it.unibo.ai.didattica.competition.tablut.board.model.State.Turn;
import lombok.Data;

/**
 * This class represents an action of a player
 * 
 * Documentation from Tablut2019 project
 * 
 * @author a.fontana
 */
@Data
public class Action {
	
	/**
	 * Corresponds to the starting cell of the action in the form L#
	 */
	private String from;
	
	/**
	 * Corresponds to the final cell of the action in the form L#
	 */
	private String to;
	
	/**
	 * Represents the {@link Turn}
	 */
	private Turn turn;

}
