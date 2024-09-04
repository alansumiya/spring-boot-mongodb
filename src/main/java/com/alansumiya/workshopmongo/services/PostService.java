package com.alansumiya.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alansumiya.workshopmongo.domain.Post;
import com.alansumiya.workshopmongo.repository.PostRepository;
import com.alansumiya.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	
	public Post FindById(String id) {
		Optional<Post> obj = repository.findById(id);
		//vai tentar dar o get, se n tiver eu lanço uma exceção
		return obj.orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
	public List<Post> findByTitle(String text){
		return repository.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		//acrescento um dia para comparar com a meia noite do próximo dia
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60* 1000);
		return repository.fullSearch(text, minDate, maxDate);
	}
	
	
}
