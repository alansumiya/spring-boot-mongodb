package com.alansumiya.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.alansumiya.workshopmongo.domain.User;

@Repository
//com essa extensão eu posso fazer várias operações com o banco de dados
public interface UserRepository extends MongoRepository<User, String>{
	
}
