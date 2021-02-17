package it.unibo.ai.didattica.competition.tablut.board.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

/**
 * Configuration class for tables
 * 
 * @author a.fontana
 */
@Configuration
@ConfigurationProperties( prefix = "app-configuration.table" )
@Getter
@Setter
public class TableConfiguration {

	/**
	 * Page size of the tables in the application
	 */
	private int pageSize;

}
