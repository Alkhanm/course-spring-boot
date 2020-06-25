package com.alkham.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.alkham.entities.Category;
import com.alkham.entities.Order;
import com.alkham.entities.User;
import com.alkham.entities.enums.OrderStatus;
import com.alkham.repositories.CategoryRepository;
import com.alkham.repositories.OrderRepository;
import com.alkham.repositories.UserRepository;

@Configuration
@Profile("test")
/* 1.1 Referencia ao nome definido em 'spring.profiles.active=test' no arquivo 'application.properties'
 * 1.2 Define que essa classe de configuração só será usada no perfil de teste. */
public class TestConfig implements CommandLineRunner { //2.1 Uma interface q implementa metodos para executar certos comandos quando o app é iniciado.
	
	@Autowired //3.1 Maneira como o Spring irá fazer um injeção de dependência dentro desta classe.
	private UserRepository userRepository; //3.2 Um dependência fraca de UserRepository feita automaticamente pelo Spring. 
	//3.3 Nada de = new Repository(), o Spring se encarrega disso, associando uma instância.
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override //2.1 Metodo q será executado na inicialização...
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		User u3 = new User(null, "John Black", "black@outlook.com", "5854543", "6534234");
		User u4 = new User(null, "Wall White", "wall@hotmail.com", "977777777", "56756734");
		User u5 = new User(null, "Marco", "mpolo@gmail.com", "8885768", "465457");
		User u6 = new User(null, "Williams", "liaams@gmail.com", "54577777", "45457677");
		userRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5, u6));
		
		Order o1 = new Order(null, Instant.parse("2017-02-12T19:53:07Z"), OrderStatus.DELIVERED, u1);
		Order o2 = new Order(null, Instant.parse("2018-01-25T03:42:10Z"), OrderStatus.PAID, u2);
		Order o3 = new Order(null, Instant.parse("2019-06-30T15:21:22Z"), OrderStatus.CANCELED, u3);
		Order o4 = new Order(null, Instant.parse("2020-07-12T15:21:22Z"), OrderStatus.CANCELED, u4);
		Order o5 = new Order(null, Instant.parse("2020-07-02T15:21:22Z"), OrderStatus.DELIVERED, u5);
		Order o6 = new Order(null, Instant.parse("2020-06-01T15:21:22Z"), OrderStatus.DELIVERED, u6);
		Order o7 = new Order(null, Instant.parse("2020-07-03T15:21:22Z"), OrderStatus.DELIVERED,u2);
		Order o8 = new Order(null, Instant.parse("2020-08-06T15:21:22Z"), OrderStatus.PAID, u1);
		orderRepository.saveAll(Arrays.asList(o1, o2, o3, o4, o5, o6, o7, o8));
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		
	}
}







