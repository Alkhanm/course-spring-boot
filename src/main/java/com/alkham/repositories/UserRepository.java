package com.alkham.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkham.entities.User;

/* Para ter acesso aos dados de certa entidade, basta passar o nome da entidade e o tipo de id que ela usa.
 * O JPA já possui um implementação padrão para essa interface JpaRepository, 
 * por esse motivo ñ é necessario criar um implemetação para essa interface que acaba de ser criada, 
 * o frameWork já cuidou disso. */
@Repository //Essa anotação é opcional, pois JpaRepository já possui está configurado como um componente do Spring.
public interface UserRepository extends JpaRepository<User, Long> {
	
	
}
