package com.eic.springchatapp.repository;



import org.springframework.data.mongodb.repository.MongoRepository;

import com.eic.springchatapp.model.Room;





public interface RoomRepository extends MongoRepository<Room,String> {
	
	public Room findByName(String name);
	public Room findByNameAndPassword(String name, String password);
}
