package com.mds.back.battle.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WsLog {

    private static final Logger logger = LoggerFactory.getLogger(WsLog.class);
    private int count = 0;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
    	count += 1;
        logger.info("WebSockets +1 --> " + count);
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
    	count -= 1;
        logger.info("WebSockets -1 --> " + count);
    }
}