package com.mds.back.battle.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WsEnabler implements WebSocketMessageBrokerConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(WsEnabler.class);
    public static final String SOCKET = "/socket";
    public static final String TOPIC = "/topic";
    public static final String APP = "/app";
	
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {    
        registry.enableSimpleBroker(TOPIC);
        registry.setApplicationDestinationPrefixes(APP);
        logger.info("WebSocket|App publish at "+APP);
        logger.info("WebSocket|Topics at "+TOPIC);
    }

	@Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(SOCKET).withSockJS();
        logger.info("WebSocket|Stomp at "+SOCKET);
    }
}
