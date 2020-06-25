package com.alkham.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.alkham.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT") //Formatar a data para o padrão ISO-8601
	private Instant moment;
	
	private Integer orderStatus;
	
	@ManyToOne //Para o JPA saber que o relacionamento com User é de MUITOS para UM
	@JoinColumn(name="client_id") //Nome da chave estrangeira que haverá nesta table
	private User client; //Um pedido(Order) pertence a um cliente (User), e um cliente pode ter um ou varios pedidos
	
	
	public Order() {
	}
	public Order(Long id, Instant moment,OrderStatus orderStatus, User client ) {
		this.id = id;
		this.moment = moment;
		this.setClient(client);
		this.setOrderStatus(orderStatus);
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Instant getMoment() {
		return moment;
	}
	public void setMoment(Instant moment) {
		this.moment = moment;
	}
	public User getClient() {
		return client;
	}
	public void setClient(User client) {
		this.client = client;
	}
	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		if (orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((moment == null) ? 0 : moment.hashCode());
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
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (moment == null) {
			if (other.moment != null)
				return false;
		} else if (!moment.equals(other.moment))
			return false;
		return true;
	}
	
}
