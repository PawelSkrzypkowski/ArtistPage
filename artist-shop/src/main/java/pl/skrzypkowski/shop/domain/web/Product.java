package pl.skrzypkowski.shop.domain.web;

import java.io.Serializable;
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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "product")
@Where(clause = "active!=false")
@Data
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private int id;
	
	@NotEmpty
	@Length(max = 100)
	@Column(name = "name", nullable = false)
	private String name;
	
	@NotEmpty
	@Length(max = 1000)
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "image", nullable = true)
	private String image;
	
	@NotNull // with Integer cannot use @NotEmpty 
	@Max(value = 1000000)
	@Min(value = 10000)
	@Column(name = "price", nullable = false)
	private Integer price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category_id", referencedColumnName = "id")
	@JsonBackReference
	private Category category;
	
	@OneToMany(mappedBy = "pk.product", cascade = CascadeType.ALL)
	@JsonBackReference
	private Set<Item> items = new HashSet<>();

	@Column(name = "active")
	private Boolean active;
	
	public Product() {
		super();
		this.image = null;
		this.active = true;
	}
	
	public Product(String name, String description, int price, String image, Category category) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = image;
		this.category = category;
		this.active = true;
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public void removeItem(Item item) {
		items.remove(item);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		if (id != other.id)
			return false;
		return true;
	}
	
}