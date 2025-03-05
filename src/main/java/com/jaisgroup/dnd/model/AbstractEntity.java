package com.jaisgroup.dnd.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@Data
public abstract class AbstractEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;
    @Column()
    @CreationTimestamp
    protected Date created;
    @Column()
    @UpdateTimestamp
    protected Date updated;

    protected AbstractEntity() {
    }
}
