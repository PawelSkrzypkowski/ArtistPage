package pl.pawelskrzypkowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.pawelskrzypkowski.entity.MailingMember;

@Repository
public interface MailingMemberRepository extends JpaRepository<MailingMember, Long> {
    @Query("update MailingMember mm set mm.active=false where mm.id=?1")
    @Modifying
    @Transactional
    void setActiveFalse(Long id);
}
