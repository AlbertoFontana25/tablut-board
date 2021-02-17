package it.unibo.ai.didattica.competition.tablut.board.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

/**
 * Represents a match. Database entity
 * 
 * @author a.fontana
 */
@Data
@Entity
@Table( name = "matches" )
public class Match {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idMatch;
	
	@CreationTimestamp
	private Instant creationDate;
	
	private Instant scheduledDate;
	
	@ManyToOne
	@JoinColumn( name = "id_white_player" )
	private Player whitePlayer;
	
	@ManyToOne
	@JoinColumn( name = "id_black_player" )
	private Player blackPlayer;
	
	@ManyToOne
	@JoinColumn( name = "id_tournament" )
	private Tournament tournament;
	
}