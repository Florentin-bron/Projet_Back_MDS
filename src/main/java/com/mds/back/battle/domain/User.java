package com.mds.back.battle.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	public enum Role { Deny, Player, Admin}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	public String name;
	public String email;
	public String hash;
	public Role role = Role.Deny;
	public int xp = 0;
	
	protected User() {}
	
	public User(String name, String email, Role role) {
		this.name = name;
		this.email = email;
		this.role = role;
	}
	
	public Long getId() {
		return id;
	}
}
