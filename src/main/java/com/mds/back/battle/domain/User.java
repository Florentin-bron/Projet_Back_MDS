package com.mds.back.battle.domain;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@DiscriminatorValue("USER")
@Entity
public class User extends Player {

	public String email;
	public String hash;
	public int xp = 0;
	public int game_number;
	public int victory_number;
	public int loose_number;

	@ManyToOne
	public Role role;

	@ManyToMany
	public List<User> friends;

	protected User() {
	}

	public User(String name, String email) {
		super(name);
		this.email = email;
	}

	@Override
	public Long getId() {
		return id;
	}
}
