package com.alkham.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String description;
	private Double price;
	private String imgUrl;
	
	@ManyToMany
	@JoinTable(name = "tb_product_category", /* Define uma tabela que irá fazer a asysociação muitos/muitos entre Products/Category. */ 
			  joinColumns = @JoinColumn(name ="product_id"),/* A coluna que irá guardar o Id desta tabela. */
			  inverseJoinColumns = @JoinColumn(name="category_id"))/* A coluna que irá guardar o nome da outra tabela. */
	private Set<Category> categories = new HashSet<>(); /* O 'Set' evita que um produto posso ter mais de uma categoria. */
	
	@OneToMany(mappedBy = "id.product")
	private Set<OrderItem> items = new HashSet<>(); //Lista de pedidos deste produto.
	
	public Product() {
	}
	public Product(Long id, String name, String description, Double price, String imgUrl) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Set<Category> getCategories() {
		return categories;
	}
	
	@JsonIgnore
	public Set<Order> getOrders(){ //Irá retorna as lista de pedidos(Order) associados a esse produto(Product)
		Set<Order> orders = new HashSet<>();
		for(OrderItem x : items) { //Para cada elemento dentro da lista 'items' de OrdermItem
			orders.add(x.getOrder()); //Pegue o pedido (Order) e adicione na lista 'orders'
		}
		return orders; //Então devolva essa lista. O que interessa é apenas pegar a lista de pedidos, não os outros items(Product) associados nestes pedidos.
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	
	
}
