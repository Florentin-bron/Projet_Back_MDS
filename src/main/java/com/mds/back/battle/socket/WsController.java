package com.mds.back.battle.socket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WsController {
	
	@MessageMapping("/"+WsBroker.CHANNEL)
	@SendTo(WsEnabler.TOPIC+"/"+WsBroker.CHANNEL)
	public WsEvent sendLobby(WsEvent event) {
	    return event;
	}
}
