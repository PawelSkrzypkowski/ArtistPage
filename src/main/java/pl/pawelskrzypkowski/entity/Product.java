package pl.pawelskrzypkowski.entity;

import lombok.Data;
import org.hibernate.annotations.Where;
import pl.pawelskrzypkowski.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

/**
 * Created by Paweł Skrzypkowski
 * Wojskowa Akademia Techniczna im. Jarosława Dąbrowskiego, Warszawa 2018.
 */

@Entity
@Data
@Where(clause = "active!=false")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Boolean isUniqueProduct;

    @ManyToOne
    @JoinColumn(name = "uniqueProductId")
    private UniqueProduct uniqueProduct;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @Column
    private String name;

    @Column
    @Lob
    private String description;

    @Column
    private String value;
}
