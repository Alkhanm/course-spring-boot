package com.alkham.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.alkham.entities.User;
import com.alkham.repositories.UserRepository;

@Configuration
@Profile("test")
/* 1.1 Referencia ao nome definido em 'spring.profiles.active=test' no arquivo 'application.properties'
 * 1.2 Define que essa classe de configuração só será usada no perfil de teste. */
public class TestConfig implements CommandLineRunner { //2.1 Uma interface q implementa metodos para executar certos comandos quando o app é iniciado.
	
	@Autowired //3.1 Maneira como o Spring irá fazer um injeção de dependência dentro desta classe.
	private UserRepository userRepository; //3.2 Um dependência fraca de UserRepository feita automaticamente pelo Spring. 
	//3.3 Nada de = new Repository(), o Spring se encarrega disso, associando uma instância.

	@Override //2.1 Metodo q será executado na inicialização...
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		User u3 = new User(null, "John Black", "black@outlook.com", "5854543", "6534234");
		User u4 = new User(null, "Wall White", "wall@hotmail.com", "977777777", "56756734");
		User u5 = new User(null, "Marco", "mpolo@gmail.com", "8885768", "465457");
		User u6 = new User(null, "Williams", "liaams@gmail.com", "54577777", "45457677");
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5, u6));
		
	}
	
	
	
}
