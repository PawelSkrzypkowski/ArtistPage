package pl.pawelskrzypkowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pawelskrzypkowski.entity.MailingMember;

@Repository
public interface MailingMemberRepository extends JpaRepository<MailingMember, Long> {
}
