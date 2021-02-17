package it.unibo.ai.didattica.competition.tablut.board.websocket;

import java.time.Instant;

import it.unibo.ai.didattica.competition.tablut.board.model.StateWrapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Socket message that wrap the {@link StateWrapper} of a match
 * 
 * @author a.fontana
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class SocketMessage {
	
	/**
	 * Sender of the message. Usually 'web-app'
	 */
	@NonNull
	private String sender;
	
	/**
	 * State of the game as {@link StateWrapper}
	 */
	@NonNull
	private StateWrapper state;
	
	/**
	 * Creation date of the message
	 */
	private Instant creationDate = Instant.now();
}
