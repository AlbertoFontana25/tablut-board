package it.unibo.ai.didattica.competition.tablut.board.model;

import java.time.Instant;

import lombok.Data;

/**
 * Simple class that represents the result of an action of start
 * 
 * @author a.fontana
 */
@Data
public class StartOperationResult {

	/**
	 * {@link Instant} of start
	 */
	private Instant startDate;
	
	/**
	 * Result of the operation
	 */
	private StartResult result;
	
	/**
	 * Enum that represent the result of a start operation
	 * 
	 * @author a.fontana
	 */
	public enum StartResult {
		STARTED, ERROR;
	}
}
