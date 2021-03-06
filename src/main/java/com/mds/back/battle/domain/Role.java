 package com.mds.back.battle.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Role {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	public String name;
	
	protected Role() {}
	
	public Role(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
}