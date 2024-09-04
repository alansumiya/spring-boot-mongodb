package com.alansumiya.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alansumiya.workshopmongo.domain.Post;
import com.alansumiya.workshopmongo.resources.util.URL;
import com.alansumiya.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	
	@Autowired
	private PostService service;
	
	
	@GetMapping(value = "/{id}")
	//ele vai encapsular toda uma estrutura necessária para a gente retornar respostas http já
	//com possíveis cabeçalhos, possíveis erros em diante
	public ResponseEntity<Post> findById(@PathVariable String id){
		
		Post obj = service.FindById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	@GetMapping(value = "/titlesearch")
	//@PathVariable vc passa uma variável de url com uma barra/, já para pesquisa vc passa como 
	//parâmetro usando ?, nesse caso usa @requestParam e especificar o nome do parâmetro (text)
	//se o parâmetro não for informado, coloco vazio nele
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/fullsearch")
	//@PathVariable vc passa uma variável de url com uma barra/, já para pesquisa vc passa como 
	//parâmetro usando ?, nesse caso usa @requestParam e especificar o nome do parâmetro (text)
	//se o parâmetro não for informado, coloco vazio nele
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate){
		text = URL.decodeParam(text);
		//se der errado na data minima ele joga a data inicial (1970)
		Date min = URL.converDate(minDate, new Date(0L));
		//se der errado ele gera com a data atual do sistema
		Date max = URL.converDate(maxDate, new Date());
		List<Post> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}
	
}
