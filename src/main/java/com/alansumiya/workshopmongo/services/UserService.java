package com.alansumiya.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alansumiya.workshopmongo.domain.User;
import com.alansumiya.workshopmongo.dto.UserDTO;
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
	
	//método que vai salvar um usuário novo no repositório
		public User insert(User obj) {
			return repository.save(obj);
		}
		
		public void delete(String id) {
			FindById(id);
			repository.deleteById(id);
		}
	
	//para questões de manutenção esse método está sendo criado aqui em vez de ser criado na
	//classe DTO
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
