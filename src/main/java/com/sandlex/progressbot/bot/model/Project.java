package com.sandlex.progressbot.bot.model;

import com.sandlex.progressbot.cache.CacheableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Project extends BaseEntity implements CacheableEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    @NotEmpty
    @Column(unique = true, nullable = false)
    private String name;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date started;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date completed;

    @NotEmpty
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotEmpty
    @Column(nullable = false)
    private Boolean percentage;

    @NotEmpty
    @Column(nullable = false)
    private Integer goal;

}
