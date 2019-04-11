package pl.pawelskrzypkowski.entity.base;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.io.Serializable;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {
    @Column(name = "ACTIVE", length = 1)
    private Boolean active = true;


    @PrePersist
    void preInsert() {
        this.active = true;
    }

}
