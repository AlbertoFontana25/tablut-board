package it.unibo.ai.didattica.competition.tablut.board.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Transformers;
import org.springframework.integration.handler.LoggingHandler.Level;
import org.springframework.integration.ip.dsl.Tcp;
import org.springframework.integration.ip.tcp.serializer.ByteArrayLengthHeaderSerializer;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import it.unibo.ai.didattica.competition.tablut.board.model.StateWrapper;
import it.unibo.ai.didattica.competition.tablut.board.websocket.SocketMessage;

/**
 * Configuration class that configure the TCP integration flow used for the
 * connection with the Tablut2019 main application
 * 
 * @author a.fontana
 */
@Configuration
public class IntegrationFlowConfiguration {
	
	/**
	 * {@link SimpMessagingTemplate} used to write on the web socket
	 */
	private SimpMessagingTemplate template;
	
	/**
	 * @param template
	 */
	@Autowired
	public IntegrationFlowConfiguration(SimpMessagingTemplate template) {
		this.template = template;
	}

	/**
	 * TCP server on port 5000
	 * 
	 * @return
	 */
	@Bean
	public IntegrationFlow server() {
		return IntegrationFlows
				.from(Tcp.inboundGateway(Tcp.netServer(5000).deserializer(new ByteArrayLengthHeaderSerializer())))
				.transform(Transformers.fromJson(StateWrapper.class))
				.<StateWrapper>handle((payload, headers) -> {
					template.convertAndSend("/match/current" ,new SocketMessage("web-app", payload));
					return payload;
				})
				.log(Level.DEBUG)
				.get();
	}
}
