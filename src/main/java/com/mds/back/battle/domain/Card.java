package com.mds.back.battle.domain;

import java.util.Arrays;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Card {
	public enum Kingdom {
		Water, Wood, Fire, Earth, Metal
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	public String name;
	@Enumerated
	public Kingdom kingdom;
	@ElementCollection
	@Enumerated
	public List<Kingdom> targets;
	public int lifePoints;
	public int damage;
	public int xp;

	protected Card() {}

	public Card(String name, Kingdom kingdom, int lifePoints, int damage, int xp, Kingdom... targets) {
		this.name = name;
		this.kingdom = kingdom;
		this.targets = Arrays.asList(targets);
		this.lifePoints = lifePoints;
		this.damage = damage;
		this.xp = xp;
		
	}

	public Long getId() {
		return id;
	}
}
