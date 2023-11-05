package com.halim.configmodel;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.event.AuditingEventListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;
@Getter
@Setter
@MappedSuperclass

@EntityListeners(AuditingEventListener.class)
public abstract class BaseObject {


    @JsonIgnore
    private boolean deleted = false;

    @JsonIgnore
    private boolean updated = false;

    @JsonIgnore
    private  boolean isActive = true;

    @CreatedBy
    private String createdByUser;

//    @Indexed
    @CreatedDate
    private Date creationDate = new Date();

    @LastModifiedDate
    private Date lastModifiedDate;

    @LastModifiedBy
    private String lastModifiedUserId;

    @Version
    private long version;
    // GETTER SETTER
}

