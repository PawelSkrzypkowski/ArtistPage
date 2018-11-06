package pl.pawelskrzypkowski.entity;

import lombok.Data;
import pl.pawelskrzypkowski.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class MailingMember extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String email;
}
