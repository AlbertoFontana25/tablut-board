package it.unibo.ai.didattica.competition.tablut.board.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

/**
 * Configuration class for admin
 * 
 * @author a.fontana
 */
@Configuration
@ConfigurationProperties( prefix = "app-configuration.admin" )
@Getter
@Setter
public class AdminConfiguration {

	/**
	 * username of the admin
	 */
	private String username;
	
	/**
	 * password of the admin
	 */
	private String password;
	
}
