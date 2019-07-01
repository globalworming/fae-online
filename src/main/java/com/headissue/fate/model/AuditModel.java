package com.headissue.fate.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditModel implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  @CreatedDate
  // TODO Instant?
  private Date createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false)
  @LastModifiedDate
  private Date updatedAt;

  public void setId(Long id) {
    this.id = id;
  }


  public Long getId() {
    return this.id;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getCreatedAt() {
    return this.createdAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Date getUpdatedAt() {
    return this.updatedAt;
  }

}