package com.alkham.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.alkham.entities.Category;
import com.alkham.entities.Order;
import com.alkham.entities.OrderItem;
import com.alkham.entities.Payment;
import com.alkham.entities.Product;
import com.alkham.entities.User;
import com.alkham.entities.enums.OrderStatus;
import com.alkham.repositories.CategoryRepository;
import com.alkham.repositories.OrderItemRepository;
import com.alkham.repositories.OrderRepository;
import com.alkham.repositories.ProductRepository;
import com.alkham.repositories.UserRepository;

@Configuration
@Profile("test")
/*
 * 1.1 Referencia ao nome definido em 'spring.profiles.active=test' no arquivo
 * 'applicategoryion.properties' 1.2 Define que essa classe de configuração só será
 * usada no perfil de teste.
 */
public class TestConfig implements CommandLineRunner { // 2.1 Uma interface q implementa metodos para executar certos
														// comandos quando o app é iniciado.

	@Autowired // 3.1 Maneira como o Spring irá fazer um injeção de dependência dentro desta
				// classe.
	private UserRepository userRepository; // 3.2 Um dependência fraca de UserRepository feita automaticamente pelo
											// Spring.
	// 3.3 Nada de = new Repository(), o Spring se encarrega disso, associando uma
	// instância.
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CategoryRepository categoryegoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override //2.1 Metodo q será executado na inicialização...
	public void run(String... args) throws Exception {
		

		Category category1 = new Category(null, "Electronics");
		Category category2 = new Category(null, "Books");
		Category category3 = new Category(null, "Computers");
		categoryegoryRepository.saveAll(Arrays.asList(category1, category2, category3));
	
		Product product1 = new Product (null, "The Lord of the Rings", "Lorem iproductsum dolor sit amet, consectetur.", 90.5, "");
				product1.getCategories().add(category2);
		Product product2 = new Product (null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
				product2.getCategories().addAll(Arrays.asList(category1, category2));
		Product product3 = new Product (null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
				product3.getCategories().add(category3);
		Product product4 = new Product (null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
				product4.getCategories().add(category3);
		Product product5 = new Product (null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
				product5.getCategories().add(category2);
		productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5));
		
		User user1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User user2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		User user3 = new User(null, "John Black", "black@outlook.com", "5854543", "6534234");
		User user4 = new User(null, "Wall White", "wall@hotmail.com", "977777777", "56756734");
		User user5 = new User(null, "Marco", "mpolo@gmail.com", "8885768", "465457");
		User user6 = new User(null, "Williams", "liaams@gmail.com", "54577777", "45457677");
		userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5, user6));
		
		Order order1 = new Order(null, Instant.parse("2017-02-12T19:53:07Z"), OrderStatus.DELIVERED, user1);
		Order order2 = new Order(null, Instant.parse("2018-01-25T03:42:10Z"), OrderStatus.PAID, user2);
		Order order3 = new Order(null, Instant.parse("2019-06-30T15:21:22Z"), OrderStatus.CANCELED, user3);
		Order order4 = new Order(null, Instant.parse("2020-07-12T15:21:22Z"), OrderStatus.CANCELED, user4);
		Order order5 = new Order(null, Instant.parse("2020-07-02T15:21:22Z"), OrderStatus.DELIVERED, user5);
		Order order6 = new Order(null, Instant.parse("2020-06-01T15:21:22Z"), OrderStatus.DELIVERED, user6);
		Order order7 = new Order(null, Instant.parse("2020-07-03T15:21:22Z"), OrderStatus.DELIVERED,user2);
		Order order8 = new Order(null, Instant.parse("2020-08-06T15:21:22Z"), OrderStatus.PAID, user1);
		orderRepository.saveAll(Arrays.asList(order1, order2, order3, order4, order5, order6, order7, order8));
		
		OrderItem orderItem1 = new OrderItem(order1, product1, 2);
		OrderItem orderItem2 = new OrderItem(order1, product3, 1);
		OrderItem orderItem3 = new OrderItem(order2, product3, 1);
		OrderItem orderItem4 = new OrderItem(order3, product4, 3);
		OrderItem orderItem5 = new OrderItem(order4, product2, 3);
		OrderItem orderItem6 = new OrderItem(order5, product2, 7);
		OrderItem orderItem7 = new OrderItem(order6, product1, 5);
		OrderItem orderItem8 = new OrderItem(order7, product3, 6);
		OrderItem orderItem9 = new OrderItem(order8, product5, 8);
		OrderItem orderItem10 = new OrderItem(order8, product5, 8);
		orderItemRepository.saveAll(Arrays.asList(orderItem1,orderItem2,orderItem3,orderItem4, orderItem5, orderItem6, orderItem7, orderItem8, orderItem9, orderItem10));
		
		Payment pay1 = new Payment(null,Instant.parse("2017-02-12T22:53:07Z"), order1);
		order1.setPayment(pay1); //Associa o pagamento ao pedido que foi pago
		orderRepository.save(order1); //Chama o JPA novamente que, por padrão, já se encarrega de salvar o pagamento associado
		
		Payment pay2 = new Payment(null,Instant.parse("2020-08-06T16:21:22Z"), order8);
		order8.setPayment(pay2);
		orderRepository.save(order8);
	}
}
