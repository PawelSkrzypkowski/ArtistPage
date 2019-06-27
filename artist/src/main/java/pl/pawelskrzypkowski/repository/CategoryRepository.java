package pl.pawelskrzypkowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.pawelskrzypkowski.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("update Category c set c.active=false where c.id=?1")
    @Modifying
    @Transactional
    void setActiveFalse(Long id);
}
