package com.mamalimomen.base.domains;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class BaseEntity<PK extends Serializable> implements Serializable {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false, unique = true)
    private PK id;

    public PK getId() {
        return id;
    }

    public void setId(PK id) {
        this.id = id;
    }
}
