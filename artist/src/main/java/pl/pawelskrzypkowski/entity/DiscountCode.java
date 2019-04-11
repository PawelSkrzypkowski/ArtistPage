package pl.pawelskrzypkowski.entity;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Paweł Skrzypkowski
 * Wojskowa Akademia Techniczna im. Jarosława Dąbrowskiego, Warszawa 2018.
 */

@Entity
@Data
@Where(clause = "active!=false")
public class DiscountCode {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    String code;

    @Column
    Integer activeLicenses;

    @Column
    Date validUntil;

    @Column
    String howItWorks;
}
