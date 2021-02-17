package it.unibo.ai.didattica.competition.tablut.board.configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

/**
 * Configuration class for game server
 * 
 * @author a.fontana
 */
@Configuration
@ConfigurationProperties( prefix = "app-configuration.game-server" )
@Getter
@Setter
public class GameServerConfiguration {

	/**
	 * Name of the script of the game server script
	 */
	private String serverScript;
	
	/**
	 * List of args for the server script
	 */
	private List<String> args;

	/**
	 * Base path of the shell scripts
	 */
	private String basePath;
	
	
}
