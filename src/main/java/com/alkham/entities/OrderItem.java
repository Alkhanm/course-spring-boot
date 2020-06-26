package com.alkham.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.alkham.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

/* Classe encarregada de fazer a associação entre produtos(Product) e pedidos (Order). 
 * Mas além disso, possui alguns atributos próprios. */
@Entity
@Table(name = "tb_order_item") 
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();
	
	private Integer quantity;
	private Double price;
	
	public OrderItem() {
	}
	public OrderItem(Order order, Product product, Integer quantity) {
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = product.getPrice();
	}
	
	
	@JsonIgnore //Evitar o loop infinito dos Jsons associados (neste caso, fica no metodo get pq é ele quem chama pedido(Order))
	public Order getOrder() {
		return id.getOrder();
	}
	public void setOrder(Order order) {
		this.id.setOrder(order);
	}
	
	
	public Product getProduct() {
		return id.getProduct();
	}
	public void setProduct(Product product) {
		this.id.setProduct(product);
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Double getSubTotal() { //Mesmo um metodo próprio deve vir no padrão 'get', para que o framework interprete e transforme em um json de resposta.
		return quantity * price;
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
		OrderItem other = (OrderItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
