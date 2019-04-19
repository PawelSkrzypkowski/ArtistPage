package pl.skrzypkowski.shop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import pl.skrzypkowski.shop.domain.web.Order;
import pl.skrzypkowski.shop.domain.web.User;

public interface OrderService {
	
	Iterable<Order> findAll();
	
	Page<Order> findLatest(int page, int size);
	
	List<Order> findByUser(User user);
	
	Order findOne(Integer id);
	
	long countAll();
	
	void save(pl.skrzypkowski.shop.domain.api.Order order);
	
	void delete(Integer orderId);
	
}
