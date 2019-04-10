package pl.pawelskrzypkowski.entity;

import lombok.Data;
import org.hibernate.annotations.Where;
import pl.pawelskrzypkowski.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Paweł Skrzypkowski
 * Wojskowa Akademia Techniczna im. Jarosława Dąbrowskiego, Warszawa 2018.
 */

@Entity
@Data
@Where(clause = "active!=false")
public class Address extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String customerName;

    @Column
    private String customerSurname;

    @Column
    private String street;

    @Column
    private String streetNumber;

    @Column
    private String apartmentNumber;

    @Column
    private String postalCode;

    @Column
    private String city;

    @Column
    private String country;
}
