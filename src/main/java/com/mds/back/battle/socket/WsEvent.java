package com.mds.back.battle.socket;

public class WsEvent {
	public String from;
	public String text;
	public WsEvent(String from, String text) {
		this.from = from;
		this.text = text;
	}
}
