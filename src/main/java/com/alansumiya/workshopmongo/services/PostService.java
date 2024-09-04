package com.alansumiya.workshopmongo.services;

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
	
	
}
