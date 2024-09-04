package com.alansumiya.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.alansumiya.workshopmongo.domain.Post;

@Repository
//com essa extensão eu posso fazer várias operações com o banco de dados
public interface PostRepository extends MongoRepository<Post, String>{
	
	//Query methods monta automaticamente uma consulta específica
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	//primeiro campo é qual o campo que eu vou efetuar a busca (title)
	//segundo campo é quem vai ser a expressão regular, que é o valor que é recebido pelo text
	//para dizer que é o primeiro parâmetro q quero aproveitar uso ?0, se tivesse mais de um e
	//quisesse aproveitar o segundo coloco ?1
	//terceiro campo coloco i para ignorar letras maiúsculas
	@Query("{ 'title' : { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
}
