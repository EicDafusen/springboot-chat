package com.eic.springchatapp.api.repository;



import org.springframework.data.mongodb.repository.MongoRepository;

import com.eic.springchatapp.api.model.Room;





public interface RoomRepository extends MongoRepository<Room,String> {
	
	public Room findByName(String name);
	public Room findByNameAndPassword(String name, String password);
}
