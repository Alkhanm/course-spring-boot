package com.alkham.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@PostMapping /*@PostMapping = diz que receberá um objeto para ser incluido*/
	public ResponseEntity<User> insert(@RequestBody User obj){ /*@RequestBody = Para dizer que um elemento será passado pelo frontend como json e, então, deverá ser deserializado e transformado em um objeto Java */ 
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).body(obj);
		/* Para ser mais semantico, ao criar uma novo elemento devesse usar o created(uri), passando o URI desse elemento criado. */
	}

}











