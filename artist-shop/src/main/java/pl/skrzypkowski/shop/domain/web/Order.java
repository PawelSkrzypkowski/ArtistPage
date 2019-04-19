package pl.skrzypkowski.shop.domain.web;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "orders")
@Where(clause = "active!=false")
@Data
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private int id;
	
	@NotEmpty
	@Column(name = "name", nullable = false)
	private String name;
	
	@NotEmpty
	@Column(name = "address", nullable = false)
	private String address;
	
	@NotEmpty
	@Column(name = "phone", nullable = false)
	private String phone;
	
	@Column(name = "note", nullable = true)
	private String note;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false)
	private Date created;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", referencedColumnName = "id")
	private User user;
	
	@OneToMany(mappedBy = "pk.order", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Item> items = new HashSet<>();

	@Column(name = "active")
	private Boolean active;
	
	public Order() {
		super();
		this.created = new Date();
		this.active = true;
	}

	public Order(String name, String address, String phone, Date created, User user) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.created = created;
		this.user = user;
		this.active = true;
	}

	public void addItem(Item item) {
		items.add(item);
	}
	
	public void removeItem(Item item) {
		items.remove(item);
	}
	
	public int getTotal() {
		int total = 0;
		for (Item e : items) {
			total += e.getSubTotal();
		}
		return total;
	}

}