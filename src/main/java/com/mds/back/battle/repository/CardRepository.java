package com.mds.back.battle.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mds.back.battle.domain.Card;
import com.mds.back.battle.domain.Card.Kingdom;

public interface CardRepository extends CrudRepository<Card, Long> {
	List<Card> findByKingdom(Kingdom kingdom);
	Card findById(long id);
}
