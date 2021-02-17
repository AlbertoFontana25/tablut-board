package it.unibo.ai.didattica.competition.tablut.board.model;

import java.time.Instant;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Represents a tournament. Database entity
 * 
 * @author a.fontana
 */
@Data
@Entity
@Table( name = "tournaments" )
public class Tournament {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idTournament;
	
	@NotEmpty( message = "Nome mancante" )
	private String name;
	
	@NotEmpty( message = "Anno mancante" )
	private String year;
	
	@CreationTimestamp
	private Instant creationDate;
	
	@OneToMany( mappedBy = "tournament" )
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private List<Team> teams;
	
	@OneToMany( mappedBy = "tournament" )
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private List<Match> matches;
	
}
