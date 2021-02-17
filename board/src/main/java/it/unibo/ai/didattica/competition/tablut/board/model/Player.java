package it.unibo.ai.didattica.competition.tablut.board.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Represents a player. Database entity
 * 
 * @author a.fontana
 */
@Data
@Entity
@Table( name = "players" )
public class Player {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idPlayer;
	
	@NotNull
	private String name;
	
	@NotNull
	@Column( columnDefinition = "text" )
	private String shellScript;
	
	@CreationTimestamp
	private Instant creationDate;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private PlayerType type;
	
	@ManyToOne( optional = false )
	@JoinColumn( name = "id_team", nullable = false )
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Team team;
	
	public enum PlayerType {
		BLACK, WHITE;
	}
	
}
