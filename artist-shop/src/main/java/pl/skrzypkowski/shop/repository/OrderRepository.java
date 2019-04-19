package pl.skrzypkowski.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.skrzypkowski.shop.domain.web.Order;
import pl.skrzypkowski.shop.domain.web.User;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	@Query("select distinct o from Order o left join fetch o.user left join fetch o.items i left join fetch i.pk.product")
	List<Order> findAll();
	
	@Query("select distinct o from Order o left join fetch o.user left join fetch o.items i left join fetch i.pk.product where o.user = ?1")
	List<Order> findByUser(User user);
	
	@Query(value = "select o from Order o left join fetch o.user left join fetch o.items i left join fetch i.pk.product",
			countQuery = "select count(o) from Order o")
	Page<Order> findLatest(Pageable pageable);
	
	@Query("select o from Order o left join fetch o.user left join fetch o.items i left join fetch i.pk.product where o.id = ?1")
	Order findOne(Integer id);

	@Modifying
	@Transactional
	@Query("UPDATE Order o SET o.active=false where o.id=?1")
	void delete(Integer id);
	
}
