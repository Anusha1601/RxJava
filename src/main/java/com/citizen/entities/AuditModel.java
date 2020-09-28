package com.citizen.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class AuditModel implements Serializable {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", nullable = false, updatable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    @CreatedDate
    private Date createdOn;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    @LastModifiedDate
    private Date updatedon;
    
    @Column(name = "created_by", nullable = false, updatable = false)
    @CreatedBy
    private String createdBy;
    
    @Column(name = "updated_by", nullable = false)
    @LastModifiedBy
    private String updatedBy;
    
    @PrePersist
    private void onCreate() {
    	createdOn = new Date();
    	createdBy = "test";
    	updatedon = new Date();
    	updatedBy = "test";
    	}
    
    @PreUpdate
    private void onUpdate() {
    	updatedon = new Date();
    	updatedBy = "test";
    	}
    
    
}
