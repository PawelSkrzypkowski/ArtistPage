package pl.skrzypkowski.shop.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import pl.skrzypkowski.shop.domain.web.Product;

public interface ProductService {

	Iterable<Product> findAll();
	
	List<Product> findLatest(int page, int size);
	
	List<Product> search(String keyword);
	
	List<Product> findByCategoryId(Integer categoryId);

	Product findOne(Integer id);
	
	Product findOneWithCategory(Integer id);

	long countAll();

	Product save(Product product);

	void delete(Integer id);
	
	Product upload(Product product, MultipartFile imageFile);

}
