package it.unibo.ai.didattica.competition.tablut.board.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Represents a role. Database entity
 * 
 * @author a.fontana
 */
@Data
@Entity
@Table( name = "user_roles" )
@NoArgsConstructor
@RequiredArgsConstructor
public class UserRole {

	@Id
	@NonNull
	private Long idUserRole;
	
	@NotNull
	@NonNull
	@Enumerated(EnumType.STRING)
	private Role roleName;
	
	public enum Role {
		USER, ADMIN;
	}
	
}
