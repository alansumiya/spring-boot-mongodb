package com.alansumiya.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alansumiya.workshopmongo.domain.User;
import com.alansumiya.workshopmongo.dto.UserDTO;
import com.alansumiya.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	//ele vai encapsular toda uma estrutura necessária para a gente retornar respostas http já
	//com possíveis cabeçalhos, possíveis erros em diante
	public ResponseEntity<List<UserDTO>> findAll(){
		
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value = "/{id}")
	//ele vai encapsular toda uma estrutura necessária para a gente retornar respostas http já
	//com possíveis cabeçalhos, possíveis erros em diante
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		
		User obj = service.FindById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@PostMapping
	//ele vai encapsular toda uma estrutura necessária para a gente retornar respostas http já
	//com possíveis cabeçalhos, possíveis erros em diante
	public ResponseEntity<UserDTO> insert(@RequestBody UserDTO objDto){
		User obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		//comando para montar uma url com o id de um novo user criado, pois a resposta do servidor
		 //para coisas novas criadas é o 201, se não fizer isso ele retorna 200
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				   .buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
