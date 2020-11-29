package com.eic.springchatapp.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eic.springchatapp.model.User;


public interface UserRepository extends MongoRepository<User, String> {

	public User findByName(String name);



}
