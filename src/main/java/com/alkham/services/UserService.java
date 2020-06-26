package com.alkham.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkham.entities.User;
import com.alkham.repositories.UserRepository;

/* As classes services são as encarregadas de fazer a ligação controllers/repositories. 
 * Elas dependem dos repositorys para pegar os dados e repassa-los para os controllers. 
 * Nelas ficará todo a parte das regras de negocio e validações. 
 * Assim o controlador precisará apenas buscar no service por essa informações, 
 * a vantagem está em enxugar as responsabilidades das classes controllers.
 * FRONTEND: Consumer ---> |BACKEND: controllers -> services -> repositories --> | database */
/* Define como um 'bean' de 'service', ou seja apartir de agora ela é um componente do Spring, 
 * isso equivale a @Componete(value="service"
 * Essa anotação faz com que essa classe seja configurada pelo Spring como injetavel. */
@Service 
public class UserService {
	
	@Autowired //Injeção de dependência
 	private UserRepository repository; //Dependência onde será buscado os dados
	
	public List<User> findAll(){
		return repository.findAll();
	}
	public User findById(Long id) {
		Optional<User> result = repository.findById(id); //Optional pega apenas objetos que possuirem valores, caso seja nulo, ele retorna um Optional vazio.
		return result.get();
	}
	
	public User insert (User obj) {
		return repository.save(obj);
	}
}
