package com.mds.back.battle.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mds.back.battle.domain.User;

public interface UserRepository extends CrudRepository<User, Long>{
	List<User> findByEmail(String email);
	User findById(long id);
}