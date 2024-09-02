package com.alansumiya.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alansumiya.workshopmongo.domain.User;
import com.alansumiya.workshopmongo.repository.UserRepository;
import com.alansumiya.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User FindById(String id) {
		Optional<User> obj = repository.findById(id);
		//vai tentar dar o get, se n tiver eu lanço uma exceção
		return obj.orElseThrow(() -> new ObjectNotFoundException(id));
	}
}
