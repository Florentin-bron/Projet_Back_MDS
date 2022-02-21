package com.mds.back.battle.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
@Entity
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	public String name;
	public int lies_number;
	public int truth_number;
	public int skip_number;
	public int contest_win_number;
	public int contest_loose_number;

	protected Player() {
	}

	public Player(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}
}
