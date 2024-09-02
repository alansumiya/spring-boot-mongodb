package com.alansumiya.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.alansumiya.workshopmongo.domain.Post;

@Repository
//com essa extensão eu posso fazer várias operações com o banco de dados
public interface PostRepository extends MongoRepository<Post, String>{
	
}
