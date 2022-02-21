package com.mds.back.battle.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("BOT")
@Entity
public class Bot extends Player {

	protected Bot() {
	}

	public Bot(String name) {
		super(name);
	}

	@Override
	public Long getId() {
		return id;
	}
}
