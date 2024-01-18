package com.gastrosfera.shared.v1.model;

import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.UUID;

public abstract class BaseModel {

    @CreatedBy
    private String createUid;

    @LastModifiedBy
    private String updateUid;

    @LastModifiedDate
    private Instant updateDate;

    @CreatedDate
    private Instant createDate;

    @Column
    private String externalUid;

    @PrePersist
    public void prePersist() {
        if (this.externalUid == null) {
            this.externalUid = UUID.randomUUID().toString();
        }
    }

}
