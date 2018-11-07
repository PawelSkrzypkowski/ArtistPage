package pl.pawelskrzypkowski.entity;

import lombok.Data;
import pl.pawelskrzypkowski.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Date;

@Entity
@Data
public class Blog extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Date addDate;

    @Column
    private String title;

    @Column
    @Lob
    private String content;

    @Column
    private String author;
}
