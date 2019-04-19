package pl.skrzypkowski.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.skrzypkowski.shop.domain.web.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Category c SET c.active=false WHERE c.id=?1")
    void delete(Integer id);

}
