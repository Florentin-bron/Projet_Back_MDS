package com.mds.back.battle.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	public String name;
	public String type;
	public int power;
	

	protected Card() {}

	public Card(String name, String type, int power) {
		this.name = name;
		this.type = type;
		this.power = power;
	}

	public Long getId() {
		return id;
	}
}
