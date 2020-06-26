package com.alkham.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.alkham.entities.User;
import com.alkham.repositories.UserRepository;
import com.alkham.services.exceptions.DatabaseException;
import com.alkham.services.exceptions.ResourceNotFoundException;

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
		return result.orElseThrow(() -> new ResourceNotFoundException(id)); //orElseThrow = tenta buscar o resultado, caso não ache lança uma exception
	}
	
	public User insert (User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
		repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public User update(Long id, User obj) {
		try{
			User entity = repository.getOne(id); //O getOne não vai ao db buscar, ele apenas monitora uma entidade que, posteriormente, terá alguma operação no db.
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}

}




