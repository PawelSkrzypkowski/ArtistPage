package pl.pawelskrzypkowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.pawelskrzypkowski.entity.Blog;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findTop3ByOrderByAddDateDesc();

    List<Blog> findAllByOrderByAddDateDesc();

    @Query("update Blog set active=false where id=?1")
    @Modifying
    @Transactional
    void setActiveFalse(Long id);
}