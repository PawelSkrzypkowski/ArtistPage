package pl.skrzypkowski.shop.domain.web;

import lombok.Data;
import org.hibernate.annotations.Where;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "item")
@AssociationOverrides({
	@AssociationOverride(
		name = "pk.order",
		joinColumns = @JoinColumn(name = "order_id")),
	@AssociationOverride(
		name = "pk.product",
		joinColumns = @JoinColumn(name = "product_id")),
})
@Where(clause = "active!=false")
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private PK pk = new PK();
	
	@Embeddable
	@Data
	private static class PK implements Serializable {

		private static final long serialVersionUID = 1L;
			
		@ManyToOne(fetch = FetchType.LAZY)
		private Order order;
		
		@ManyToOne(fetch = FetchType.LAZY)
		private Product product;
		
	}
	
	@Column(name = "quantity", nullable = false)
	private int quantity;
	
	@Column(name = "price", nullable = false)
	private int price;

	@Column(name = "active")
	private Boolean active;
	
	public Item() {
		this.active = true;
	}
	
	@Transient
	public Order getOrder() {
		return pk.getOrder();
	}
	
	public void setOrder(Order order) {
		pk.setOrder(order);
	}
	
	@Transient
	public Product getProduct() {
		return pk.getProduct();
	}

	public void setProduct(Product product) {
		pk.setProduct(product);
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	
	public int getSubTotal() {
		return pk.getProduct().getPrice() * quantity;
	}

}