package pl.pawelskrzypkowski.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;
import pl.pawelskrzypkowski.entity.base.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Where(clause = "active!=false")
public class CategoryElement extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String href;

    @Column(precision = 8, scale = 2)
    private BigDecimal price;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToOne
    private Category category;
}
