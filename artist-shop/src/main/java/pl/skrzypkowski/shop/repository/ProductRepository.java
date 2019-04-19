package pl.skrzypkowski.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.transaction.annotation.Transactional;
import pl.skrzypkowski.shop.domain.web.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("select p from Product p")
	List<Product> findAll();
	
	List<Product> findTop10ByOrderByIdDesc();

	List<Product> findByNameContaining(String keyword);
	
	@Query("select p from Product p left join fetch p.category c where c.id = ?1")
	List<Product> findByCategoryId(Integer categoryId);
	
	@Query("select p from Product p left join fetch p.category c where p.id = ?1")
	Product findOneWithCategory(Integer id);

	@Modifying
	@Transactional
	@Query("UPDATE Order p SET p.active=false where p.id=?1")
	void delete(Integer id);
}