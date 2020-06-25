package com.alkham.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkham.entities.User;
import com.alkham.services.UserService;

@RestController //Define que essa classe é reponsavel por retornar recursos Rest
@RequestMapping(value = "/users") //Define sobre quais recursos essa classe está apta a controlar
public class UserResources {
	
	@Autowired //Injeção de dependência
	private UserService service; // Dependência service, onde será buscados os dados já com as validações, regras de negocio, etc..
	
	/* ReponseEntity<T> = Um tipo especial Spring responsavel por retornar respostas de requisições Web
	 * É um tipo gênerico, assim é necessario espeficar entre <> qual tipo especifico ele podera retornar*/
	@GetMapping //Indica que irá retornar uma resposta GET
	public ResponseEntity<List<User>> findAll(){
		List<User> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}") //para mostrar apenas os usuários que tenham esse id
	public ResponseEntity<User> findById(@PathVariable Long id) { //@PathVariable para o Spring entender que esse Long id é o parametro /{id} na URL
		User result = service.findById(id);
		
		return ResponseEntity.ok().body(result);
	}
}











