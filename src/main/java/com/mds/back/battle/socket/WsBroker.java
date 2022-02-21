package com.mds.back.battle.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Component
public class WsBroker {
	private static final Logger logger = LoggerFactory.getLogger(WsBroker.class);
	public static final String CHANNEL = "main";

    @Autowired
    private SimpMessageSendingOperations sender;
    
    public void publish(String from, String text) {
    	String topic = WsEnabler.TOPIC+"/"+CHANNEL;
    	logger.info("Publish from "+from+", text "+text+" --> " + topic);
    	sender.convertAndSend(topic, new WsEvent(from, text));
    }
}
