package com.alkham.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkham.entities.User;

@RestController //Define que essa classe é reponsavel por retornar recursos Rest
@RequestMapping(value = "/users") //Define sobre quais recursos essa classe está apta a controlar
public class UserResources {
	
	/* ReponseEntity<T> = Um tipo especial Spring responsavel por retornar respostas de requisições Web
	 * É um tipo gênerico, assim é necessario espeficar entre <> qual tipo especifico ele podera retornar*/
	@GetMapping //Indica que irá retornar uma resposta GET
	public ResponseEntity<User> finAll(){
		User u = new User(2L, "Maria", "Maria@gmail.com", "fsdfsdf", "fsdf");
		
		return ResponseEntity.ok().body(u);
	}

}
