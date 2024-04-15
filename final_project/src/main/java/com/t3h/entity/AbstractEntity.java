package com.t3h.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CREATED_BY")
    private int createdBy;
    @Column(name = "CREATED_TIME")
    private LocalDateTime createdTime;
    @Column(name = "UPDATED_BY")
    private int updatedBy;
    @Column(name = "UPDATED_TIME")
    private LocalDateTime updatedTime;

}
