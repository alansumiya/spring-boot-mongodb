package com.alansumiya.workshopmongo.repository;

import java.util.Date;
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
	
	//comando está dizendo que a procura tem q estar entre essas 2 datas
	//gte: maior que o parâmetro 1 (minDate)
	//lte: menor que o parâmetro 2 (maxDate)
	//segunda parte deve fazer a pesquisa em:
	//1º busca no título do post
	//2º busca no corpo do post
	//3º busca na lista de comments em post e dentro dela tem o texto (commentDTO)
	
	@Query("{ $and: [ { date: {$gte: ?1} },"
			+ "{ date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } },"
			+ "{ 'body': { $regex: ?0, $options: 'i' } },"
			+ "{ 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")

	List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
