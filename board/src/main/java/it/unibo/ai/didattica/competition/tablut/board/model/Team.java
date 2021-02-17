package it.unibo.ai.didattica.competition.tablut.board.model;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import it.unibo.ai.didattica.competition.tablut.board.model.Player.PlayerType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Represents a team. Database entity
 * 
 * @author a.fontana
 */
@Data
@Entity
@Table( name = "teams" )
public class Team {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idTeam;
	
	@NotNull
	private String name;
	
	@CreationTimestamp
	private Instant creationDate;
	
	@ManyToOne( optional = false )
	@JoinColumn( name = "id_tournament", nullable = false )
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Tournament tournament;
	
	@OneToMany( mappedBy = "team" )
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private List<Player> players;
	
	public Optional<Player> getWhite() {
		return getPlayerByPlayerType(PlayerType.WHITE);
	}
	
	public Optional<Player> getBlack() {
		return getPlayerByPlayerType(PlayerType.BLACK);
	}
	
	private Optional<Player> getPlayerByPlayerType(PlayerType playerType) {
		Optional<Player> playerOpt = Optional.empty();
		for(Player player : players) {
			if(player.getType().equals(playerType)) {
				playerOpt = Optional.of(player);
			}
		}
		
		return playerOpt;
	}

	
}
