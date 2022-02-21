 package com.mds.back.battle.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Success {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	public String name;
	public String description;
	
	
	protected Success() {}
	
	public Success(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public Long getId() {
		return id;
	}
}