package com.sandlex.progressbot.bot.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
public class Person extends BaseEntity {

    @NotEmpty
    @Column(unique = true, nullable = false)
    private Integer telegramId;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date registered;

}
