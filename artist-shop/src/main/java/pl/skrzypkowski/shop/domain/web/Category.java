package pl.skrzypkowski.shop.domain.web;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "category")
@Where(clause = "active!=false")
@Data
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private int id;
	
	@Length(max = 255)
	@NotEmpty
	@Column(name = "name", nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@OrderBy("id DESC")
	@JsonBackReference // prevent load products when load category (REST)
	private Set<Product> products = new HashSet<Product>();

	@Column(name = "active")
	private Boolean active;
	
	public Category() {
		this.active = true;
	}
	
	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.active = true;
	}

	public void addProduct(Product product) {
		products.add(product);
		product.setCategory(this);
	}
	
	public void removeProduct(Product product) {
		products.remove(product);
		product.setCategory(null);
	}
} 