package it.unibo.ai.didattica.competition.tablut.board.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Configuration class that enables and configure the web socket
 * 
 * @author a.fontana
 */
@Configuration
@EnableWebSocketMessageBroker
public class CustomWebSocketConfig implements WebSocketMessageBrokerConfigurer {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/match");
		config.setApplicationDestinationPrefixes("/web-socket");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/game");
		registry.addEndpoint("/game").withSockJS();
	}

}
