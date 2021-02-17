package it.unibo.ai.didattica.competition.tablut.board.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a user. Database entity
 * 
 * @author a.fontana
 */
@Entity
@Table( name = "users" )
@NamedEntityGraph( name = "user-with-roles", attributeNodes = { @NamedAttributeNode( "roles" ) } )
@NoArgsConstructor
@Setter
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idUser;
	
	@NotEmpty
	@Column( unique = true )
	private String username;
	
	@NotEmpty
	private String password;
	
	private Boolean enabled;
	
	@CreationTimestamp
	private Instant creationDate;
	
	@ManyToMany
	@JoinTable(
		name = "users_to_user_roles",
		joinColumns = @JoinColumn( name = "id_user" ),
		inverseJoinColumns = @JoinColumn( name = "id_user_role" )
	)
	private Set<UserRole> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(UserRole role : roles) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName().toString()));
		}
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return (enabled != null && enabled);
	}

}
